<template>
  <!-- 主容器，包含整个教师管理页面 -->
  <div class="teacher-management">
    <!-- 获取所有教师信息按钮 -->
    <el-button type="primary" @click="fetchTeachers">获取所有教师信息</el-button>
    <!-- 添加教师按钮 -->
    <el-button type="success" @click="openAddTeacherDialog">添加教师</el-button>
    <!-- 搜索框 -->
    <el-input
        v-model="search"
        size="default"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <!-- 教师信息表格 -->
    <el-table
        :data="teachers.filter(teacher => !search || teacher.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="code" label="工号" width="180"></el-table-column>
      <el-table-column prop="collegeName" label="学院" width="180"></el-table-column>
      <el-table-column label="操作" width="240">
        <!-- 操作按钮：编辑、删除、分配课程 -->
        <template v-slot="scope">
          <el-button @click="editTeacher(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteTeacher(scope.row.id)" type="danger" size="small">删除</el-button>
          <el-button @click="assignCourse(scope.row)" type="success" size="small">分配课程</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加/编辑教师对话框 -->
    <el-dialog v-model="dialogVisible" title="教师表单">
      <el-form :model="teacherForm" :rules="rules" ref="teacherFormRef">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="teacherForm.name"></el-input>
        </el-form-item>
        <el-form-item label="工号" prop="code">
          <el-input v-model="teacherForm.code"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="teacherForm.password"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="collegeId">
          <el-select v-model="teacherForm.collegeId" placeholder="请选择学院">
            <el-option
                v-for="college in colleges"
                :key="college.id"
                :label="college.name"
                :value="college.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveTeacher">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 分配课程对话框 -->
    <el-dialog v-model="assignCourseDialogVisible" title="分配课程">
      <el-table :data="assignedCourses" style="width: 100%">
        <el-table-column prop="name" label="课程名称" width="180"></el-table-column>
        <el-table-column prop="term.name" label="学期" width="180"></el-table-column>
        <el-table-column prop="remark" label="课程备注" width="180"></el-table-column>
        <el-table-column label="操作" width="180">
          <!-- 删除课程按钮 -->
          <template v-slot="scope">
            <el-button @click="removeCourse(scope.row)" type="danger" size="small">删除</el-button>
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
import teacherService from '@/services/teacherService';

export default {
  name: 'TeacherManagement',
  data() {
    return {
      // 存储教师信息
      teachers: [],
      // 存储学院信息
      colleges: [],
      // 教师表单数据
      teacherForm: {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        collegeId: ''
      },
      // 对话框可见性控制
      dialogVisible: false,
      assignCourseDialogVisible: false,
      // 当前选中的教师
      selectedTeacher: null,
      // 分配课程相关数据
      assignedCourses: [],
      availableCourses: [],
      selectedCourseId: null,
      // 搜索关键字
      search: '',
      // 表单验证规则
      rules: {
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        code: [{required: true, message: '请输入工号', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        collegeId: [{required: true, message: '请选择学院', trigger: 'change'}]
      }
    };
  },
  methods: {
    // 获取教师信息
    fetchTeachers() {
      teacherService.fetchTeachers()
          .then(response => {
            this.teachers = response.data.map(item => ({
              id: item.teacher.id,
              name: item.teacher.name,
              code: item.teacher.code,
              password: item.teacher.password,
              collegeId: item.teacher.collegeId,
              collegeName: item.college.name
            }));
          })
          .catch(error => {
            console.error('获取教师信息时出错:', error);
          });
    },
    // 获取学院信息
    fetchColleges() {
      teacherService.fetchColleges()
          .then(response => {
            this.colleges = response.data;
          })
          .catch(error => {
            console.error('获取学院信息时出错:', error);
          });
    },
    // 打开添加教师对话框
    openAddTeacherDialog() {
      this.teacherForm = {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        collegeId: ''
      };
      this.dialogVisible = true;
    },
    // 编辑教师信息
    editTeacher(teacher) {
      this.teacherForm = {...teacher, password: ''}; // 清除密码字段
      this.dialogVisible = true;
    },
    // 保存教师信息
    saveTeacher() {
      this.$refs.teacherFormRef.validate((valid) => {
        if (valid) {
          teacherService.saveTeacher(this.teacherForm)
              .then(() => {
                this.$message.success('教师信息保存成功');
                this.dialogVisible = false;
                this.fetchTeachers(); // 更新成功后刷新数据
              })
              .catch(error => {
                console.error('保存教师信息时出错:', error);
                this.$message.error('保存教师信息时发生错误');
              });
        } else {
          this.$message.error('请填写完整信息');
          return false;
        }
      });
    },
    // 删除教师信息
    deleteTeacher(id) {
      this.$confirm('此操作将永久删除该教师, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teacherService.deleteTeacher(id)
            .then(() => {
              this.$message.success('教师信息删除成功');
              this.fetchTeachers(); // 删除成功后刷新数据
            })
            .catch(error => {
              console.error('删除教师信息时出错:', error);
              this.$message.error('删除教师信息时发生错误');
            });
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    },
    // 分配课程
    assignCourse(teacher) {
      this.selectedTeacher = teacher;
      this.assignCourseDialogVisible = true;
      this.fetchAssignedCourses(teacher.id);
      this.fetchAvailableCourses();
    },
    // 获取已分配课程
    fetchAssignedCourses(teacherId) {
      teacherService.fetchAssignedCourses(teacherId)
          .then(courses => {
            this.assignedCourses = courses;
          })
          .catch(error => {
            console.error('获取教师课程信息时出错:', error);
          });
    },
    // 获取可选课程
    fetchAvailableCourses() {
      teacherService.fetchAvailableCourses()
          .then(response => {
            this.availableCourses = response.data;
          })
          .catch(error => {
            console.error('获取课程信息时出错:', error);
          });
    },
    // 添加课程
    addCourse() {
      if (this.assignedCourses.some(course => course.id === this.selectedCourseId)) {
        this.$message.error('该课程已分配给教师，不能重复添加');
        return;
      }
      const courseAssignment = {
        teacherId: this.selectedTeacher.id,
        courseId: this.selectedCourseId
      };
      teacherService.addCourse(courseAssignment)
          .then(() => {
            this.$message.success('课程分配成功');
            this.fetchAssignedCourses(this.selectedTeacher.id);
          })
          .catch(error => {
            console.error('课程分配时出错:', error);
            this.$message.error('课程分配时发生错误');
          });
    },
    // 删除课程
    removeCourse(course) {
      teacherService.removeCourse(this.selectedTeacher.id, course.id)
          .then(() => {
            this.$message.success('课程删除成功');
            this.fetchAssignedCourses(this.selectedTeacher.id);
          })
          .catch(error => {
            console.error('课程删除时出错:', error);
            this.$message.error('课程删除时发生错误');
          });
    }
  },
  mounted() {
    // 组件挂载时获取教师和学院信息
    this.fetchTeachers();
    this.fetchColleges();
  }
};
</script>

<style scoped>
.teacher-management {
  padding: 20px;
}
</style>
