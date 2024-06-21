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
      <el-table-column label="操作" width="180">
        <template v-slot="scope">
          <el-button @click="editTeacher(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteTeacher(scope.row.id)" type="danger" size="small">删除</el-button>
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
