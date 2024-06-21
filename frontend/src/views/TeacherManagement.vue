<template>
  <div class="teacher-management">
    <el-button type="primary" @click="fetchTeachers">获取所有教师信息</el-button>
    <el-button type="success" @click="openAddTeacherDialog">添加教师</el-button>
    <el-input
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <el-table
        :data="teachers.filter(teacher => !search || teacher.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="code" label="工号" width="180"></el-table-column>
      <el-table-column prop="collegeName" label="学院" width="180"></el-table-column>
      <el-table-column label="操作" width="240">
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
        <el-form-item v-if="!teacherForm.id" label="密码" prop="password">
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
import apiClient from '@/services/api';

export default {
  name: 'TeacherManagement',
  data() {
    return {
      teachers: [],
      colleges: [],
      teacherForm: {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        collegeId: ''
      },
      dialogVisible: false,
      assignCourseDialogVisible: false,
      selectedTeacher: null,
      assignedCourses: [],
      availableCourses: [],
      selectedCourseId: null,
      search: '',
      rules: {
        name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
        code: [{ required: true, message: '请输入工号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        collegeId: [{ required: true, message: '请选择学院', trigger: 'change' }]
      }
    };
  },
  methods: {
    fetchTeachers() {
      apiClient.get('/teacher/all')
          .then(response => {
            console.log('Fetched teachers:', response.data); // Debug line
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
    fetchColleges() {
      apiClient.get('/college/all')
          .then(response => {
            this.colleges = response.data;
          })
          .catch(error => {
            console.error('获取学院信息时出错:', error);
          });
    },
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
    editTeacher(teacher) {
      this.teacherForm = { ...teacher, password: '' }; // Clear password field
      this.dialogVisible = true;
    },
    saveTeacher() {
      this.$refs.teacherFormRef.validate((valid) => {
        if (valid) {
          const apiCall = this.teacherForm.id ? apiClient.put : apiClient.post;
          const endpoint = this.teacherForm.id ? `/teacher/update` : '/teacher/add';

          apiCall(endpoint, this.teacherForm)
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
    deleteTeacher(id) {
      this.$confirm('此操作将永久删除该教师, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        apiClient.delete(`/teacher/delete/${id}`)
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
    assignCourse(teacher) {
      this.selectedTeacher = teacher;
      this.assignCourseDialogVisible = true;
      this.fetchAssignedCourses(teacher.id);
      this.fetchAvailableCourses();
    },
    fetchAssignedCourses(teacherId) {
      apiClient.get(`/teacherCourse/teacher/${teacherId}/courses`)
          .then(response => {
            const coursePromises = response.data.map(course =>
                apiClient.get(`/terms/${course.termId}`)
                    .then(termResponse => ({
                      ...course,
                      term: termResponse.data
                    }))
            );
            Promise.all(coursePromises)
                .then(courses => {
                  this.assignedCourses = courses;
                });
          })
          .catch(error => {
            console.error('获取教师课程信息时出错:', error);
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
      if (this.assignedCourses.some(course => course.id === this.selectedCourseId)) {
        this.$message.error('该课程已分配给教师，不能重复添加');
        return;
      }
      const courseAssignment = {
        teacherId: this.selectedTeacher.id,
        courseId: this.selectedCourseId
      };
      apiClient.post('/teacherCourse/add', courseAssignment)
          .then(() => {
            this.$message.success('课程分配成功');
            this.fetchAssignedCourses(this.selectedTeacher.id);
          })
          .catch(error => {
            console.error('课程分配时出错:', error);
            this.$message.error('课程分配时发生错误');
          });
    },
    removeCourse(course) {
      apiClient.get(`/teacherCourse/teacher/${this.selectedTeacher.id}/teacherCourses`)
          .then(response => {
            const assignment = response.data.find(c => c.courseId === course.id);
            if (assignment) {
              apiClient.delete(`/teacherCourse/delete/${assignment.id}`)
                  .then(() => {
                    this.$message.success('课程删除成功');
                    this.fetchAssignedCourses(this.selectedTeacher.id);
                  })
                  .catch(error => {
                    console.error('课程删除时出错:', error);
                    this.$message.error('课程删除时发生错误');
                  });
            }
          })
          .catch(error => {
            console.error('获取教师课程信息时出错:', error);
          });
    }
  },
  mounted() {
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
