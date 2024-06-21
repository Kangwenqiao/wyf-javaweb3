<template>
  <div class="class-management">
    <el-button type="primary" @click="openAddClassDialog">增加班级</el-button>
    <el-input
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <el-table :data="filteredClasses" style="width: 100%">
      <el-table-column prop="classGroup.name" label="班级名称" width="180"></el-table-column>
      <el-table-column prop="classGroup.code" label="班级代码" width="180"></el-table-column>
      <el-table-column prop="speciality.name" label="专业名称" width="180"></el-table-column>
      <el-table-column prop="speciality.grade" label="专业年级" width="180"></el-table-column>
      <el-table-column prop="college.name" label="学院名称" width="180"></el-table-column>
      <el-table-column label="操作" width="300">
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
import apiClient from '@/services/api';

export default {
  name: 'ClassManagement',
  data() {
    return {
      classes: [],
      specialities: [],
      classForm: {
        id: null,
        name: '',
        code: '',
        specialityId: null,
        remark: ''
      },
      dialogVisible: false,
      assignCourseDialogVisible: false,
      selectedClassId: null,
      assignedCourses: [],
      availableCourses: [],
      selectedCourseId: null,
      search: '',
      rules: {
        name: [{ required: true, message: '请输入班级名称', trigger: 'blur' }],
        code: [{ required: true, message: '请输入班级代码', trigger: 'blur' }],
        specialityId: [{ required: true, message: '请选择专业', trigger: 'change' }],
        remark: [{ required: true, message: '请输入班级备注', trigger: 'blur' }]
      }
    };
  },
  computed: {
    filteredClasses() {
      return this.classes.filter(cls => !this.search || cls.classGroup.name.toLowerCase().includes(this.search.toLowerCase()));
    }
  },
  methods: {
    fetchClasses() {
      apiClient.get('/class/joined/all')
          .then(response => {
            this.classes = response.data;
          })
          .catch(error => {
            console.error('获取班级信息时出错:', error);
          });
    },
    fetchSpecialities() {
      apiClient.get('/speciality/all')
          .then(response => {
            this.specialities = response.data;
          })
          .catch(error => {
            console.error('获取专业信息时出错:', error);
          });
    },
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
    saveClass() {
      this.$refs.classFormRef.validate((valid) => {
        if (valid) {
          const apiCall = this.classForm.id ? apiClient.put : apiClient.post;
          const endpoint = this.classForm.id ? `/class` : '/class';

          const classData = {
            id: this.classForm.id,
            name: this.classForm.name,
            code: this.classForm.code,
            specialityId: this.classForm.specialityId,
            remark: this.classForm.remark
          };

          apiCall(endpoint, classData)
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
    deleteClass(id) {
      apiClient.delete(`/class/${id}`)
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
    assignCourse(classId) {
      this.selectedClassId = classId;
      this.assignCourseDialogVisible = true;
      this.fetchAssignedCourses(classId);
      this.fetchAvailableCourses();
    },
    fetchAssignedCourses(classId) {
      apiClient.get(`/classCourse/getByClassId/${classId}`)
          .then(response => {
            const coursePromises = response.data.map(courseAssignment =>
                apiClient.get(`/courses/${courseAssignment.courseId}`)
                    .then(courseResponse => {
                      const course = courseResponse.data;
                      return apiClient.get(`/terms/${course.termId}`)
                          .then(termResponse => {
                            return {
                              id: courseAssignment.id,
                              course,
                              term: termResponse.data
                            };
                          });
                    })
            );
            Promise.all(coursePromises)
                .then(courses => {
                  this.assignedCourses = courses;
                });
          })
          .catch(error => {
            console.error('获取班级课程信息时出错:', error);
          });
    },
    fetchAvailableCourses() {
      apiClient.get('/courses')
          .then(response => {
            this.availableCourses = response.data;
          })
          .catch(error => {
            console.error('获取课程信息时出错:', error);
          });
    },
    addCourse() {
      const courseAssignment = {
        classId: this.selectedClassId,
        courseId: this.selectedCourseId
      };
      apiClient.post('/classCourse/add', courseAssignment)
          .then(() => {
            this.$message.success('课程分配成功');
            this.fetchAssignedCourses(this.selectedClassId);
          })
          .catch(error => {
            console.error('课程分配时出错:', error);
            this.$message.error('课程分配时发生错误');
          });
    },
    removeCourse(assignmentId) {
      apiClient.delete(`/classCourse/delete/${assignmentId}`)
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
