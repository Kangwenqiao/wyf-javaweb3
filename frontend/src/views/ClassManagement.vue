<template>
  <!-- 主容器，包含整个班级管理页面 -->
  <div class="class-management">
    <!-- 增加班级按钮 -->
    <el-button type="primary" @click="openAddClassDialog">增加班级</el-button>
    <!-- 搜索框 -->
    <el-input
        v-model="search"
        size="default"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <!-- 班级信息表格 -->
    <el-table :data="filteredClasses" style="width: 100%">
      <el-table-column prop="classGroup.name" label="班级名称" width="180"></el-table-column>
      <el-table-column prop="classGroup.code" label="班级代码" width="180"></el-table-column>
      <el-table-column prop="speciality.name" label="专业名称" width="180"></el-table-column>
      <el-table-column prop="speciality.grade" label="专业年级" width="180"></el-table-column>
      <el-table-column prop="college.name" label="学院名称" width="180"></el-table-column>
      <el-table-column label="操作" width="300">
        <!-- 操作按钮：编辑、删除、分配课程 -->
        <template v-slot="scope">
          <el-button @click="editClass(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteClass(scope.row.classGroup.id)" type="danger" size="small">删除</el-button>
          <el-button @click="assignCourse(scope.row.classGroup.id)" type="success" size="small">分配课程</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 增加/编辑班级对话框 -->
    <el-dialog v-model="dialogVisible" title="班级表单">
      <el-form :model="classForm" :rules="rules" ref="classFormRef">
        <el-form-item label="班级名称" prop="name">
          <el-input v-model="classForm.name"></el-input>
        </el-form-item>
        <el-form-item label="班级代码" prop="code">
          <el-input v-model="classForm.code"></el-input>
        </el-form-item>
        <el-form-item label="班级备注" prop="remark">
          <el-input v-model="classForm.remark"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="specialityId">
          <el-select v-model="classForm.specialityId" placeholder="请选择专业">
            <el-option
                v-for="speciality in specialities"
                :key="speciality.id"
                :label="speciality.name"
                :value="speciality.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveClass">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 分配课程对话框 -->
    <el-dialog v-model="assignCourseDialogVisible" title="分配课程">
      <el-table :data="assignedCourses" style="width: 100%">
        <el-table-column prop="course.name" label="课程名称" width="180"></el-table-column>
        <el-table-column prop="term.name" label="学期" width="180"></el-table-column>
        <el-table-column prop="course.remark" label="课程备注" width="180"></el-table-column>
        <el-table-column label="操作" width="180">
          <!-- 课程删除按钮 -->
          <template v-slot="scope">
            <el-button @click="removeCourse(scope.row.id)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-form>
        <el-form-item label="选择课程">
          <el-select v-model="selectedCourseId" placeholder="选择课程">
            <el-option
                v-for="course in availableCourses"
                :key="course.id"
                :label="course.name"
                :value="course.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addCourse">添加课程</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import classapiService from '@/services/classapiService';

export default {
  name: 'ClassManagement',
  data() {
    return {
      // 班级列表数据
      classes: [],
      // 专业列表数据
      specialities: [],
      // 班级表单数据
      classForm: {
        id: null,
        name: '',
        code: '',
        specialityId: null,
        remark: ''
      },
      // 对话框可见性控制
      dialogVisible: false,
      assignCourseDialogVisible: false,
      // 当前选中的班级ID
      selectedClassId: null,
      // 已分配课程列表
      assignedCourses: [],
      // 可选课程列表
      availableCourses: [],
      // 当前选中的课程ID
      selectedCourseId: null,
      // 搜索关键字
      search: '',
      // 表单验证规则
      rules: {
        name: [{ required: true, message: '请输入班级名称', trigger: 'blur' }],
        code: [{ required: true, message: '请输入班级代码', trigger: 'blur' }],
        specialityId: [{ required: true, message: '请选择专业', trigger: 'change' }],
        remark: [{ required: true, message: '请输入班级备注', trigger: 'blur' }]
      }
    };
  },
  computed: {
    // 根据搜索关键字过滤班级列表
    filteredClasses() {
      return this.classes.filter(cls => !this.search || cls.classGroup.name.toLowerCase().includes(this.search.toLowerCase()));
    }
  },
  methods: {
    // 获取班级数据
    fetchClasses() {
      classapiService.fetchClasses()
          .then(response => {
            this.classes = response.data;
          })
          .catch(error => {
            console.error('获取班级信息时出错:', error);
          });
    },
    // 获取专业数据
    fetchSpecialities() {
      classapiService.fetchSpecialities()
          .then(response => {
            this.specialities = response.data;
          })
          .catch(error => {
            console.error('获取专业信息时出错:', error);
          });
    },
    // 打开增加班级对话框
    openAddClassDialog() {
      this.classForm = {
        id: null,
        name: '',
        code: '',
        specialityId: null,
        remark: ''
      };
      this.dialogVisible = true;
    },
    // 编辑班级
    editClass(classData) {
      this.classForm = {
        id: classData.classGroup.id,
        name: classData.classGroup.name,
        code: classData.classGroup.code,
        specialityId: classData.speciality.id,
        remark: classData.classGroup.remark
      };
      this.dialogVisible = true;
    },
    // 保存班级信息
    saveClass() {
      this.$refs.classFormRef.validate((valid) => {
        if (valid) {
          const classData = {
            id: this.classForm.id,
            name: this.classForm.name,
            code: this.classForm.code,
            specialityId: this.classForm.specialityId,
            remark: this.classForm.remark
          };
          classapiService.saveClass(classData)
              .then(() => {
                this.$message.success('班级信息保存成功');
                this.dialogVisible = false;
                this.fetchClasses(); // 更新成功后刷新数据
              })
              .catch(error => {
                console.error('保存班级信息时出错:', error);
                this.$message.error('保存班级信息时发生错误');
              });
        } else {
          this.$message.error('请填写完整信息');
          return false;
        }
      });
    },
    // 删除班级信息
    deleteClass(id) {
      classapiService.deleteClass(id)
          .then(() => {
            this.$message.success('班级信息删除成功');
            this.fetchClasses(); // 删除成功后刷新数据
          })
          .catch(error => {
            if (error.response && error.response.status === 500) {
              this.$message.error('该班级存在未处理的学生，请处理完学生后再删除');
            } else {
              console.error('删除班级信息时出错:', error);
              this.$message.error('删除班级信息时发生错误');
            }
          });
    },
    // 分配课程
    assignCourse(classId) {
      this.selectedClassId = classId;
      this.assignCourseDialogVisible = true;
      this.fetchAssignedCourses(classId);
      this.fetchAvailableCourses();
    },
    // 获取已分配课程数据
    fetchAssignedCourses(classId) {
      classapiService.fetchAssignedCourses(classId)
          .then(courses => {
            this.assignedCourses = courses;
          })
          .catch(error => {
            console.error('获取班级课程信息时出错:', error);
          });
    },
    // 获取可选课程数据
    fetchAvailableCourses() {
      classapiService.fetchAvailableCourses()
          .then(response => {
            this.availableCourses = response.data;
          })
          .catch(error => {
            console.error('获取课程信息时出错:', error);
          });
    },
    // 添加课程
    addCourse() {
      // 检查课程是否已经分配给该班级
      if (this.assignedCourses.some(course => course.course.id === this.selectedCourseId)) {
        this.$message.error('该课程已经分配给该班级');
        return;
      }

      const courseAssignment = {
        classId: this.selectedClassId,
        courseId: this.selectedCourseId
      };
      classapiService.addCourse(courseAssignment)
          .then(() => {
            this.$message.success('课程分配成功');
            this.fetchAssignedCourses(this.selectedClassId);
          })
          .catch(error => {
            console.error('课程分配时出错:', error);
            this.$message.error('课程分配时发生错误');
          });
    },
    // 删除课程
    removeCourse(assignmentId) {
      classapiService.removeCourse(assignmentId)
          .then(() => {
            this.$message.success('课程删除成功');
            this.fetchAssignedCourses(this.selectedClassId);
          })
          .catch(error => {
            console.error('课程删除时出错:', error);
            this.$message.error('课程删除时发生错误');
          });
    }
  },
  mounted() {
    // 组件挂载时获取班级和专业数据
    this.fetchClasses();
    this.fetchSpecialities();
  }
};
</script>

<style scoped>
.class-management {
  padding: 20px;
}
</style>
