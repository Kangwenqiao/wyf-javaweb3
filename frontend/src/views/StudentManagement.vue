<template>
  <!-- 主容器，包含整个学生管理页面 -->
  <div class="student-management">
    <!-- 获取所有学生信息按钮 -->
    <el-button type="primary" @click="fetchStudents">获取所有学生信息</el-button>
    <!-- 添加学生按钮 -->
    <el-button type="success" @click="openAddStudentDialog">添加学生</el-button>
    <!-- 搜索框 -->
    <el-input
        v-model="search"
        size="default"
        placeholder="输入关键字搜索"
        style="margin: 20px 0;"
    />
    <!-- 学生信息表格 -->
    <el-table
        :data="students.filter(student => !search || student.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%">
      <el-table-column prop="id" label="ID" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="code" label="学号" width="180"></el-table-column>
      <el-table-column prop="className" label="班级" width="180"></el-table-column>
      <el-table-column prop="specialityName" label="专业" width="180"></el-table-column>
      <el-table-column prop="collegeName" label="学院" width="180"></el-table-column>
      <el-table-column label="操作" width="180">
        <!-- 操作按钮：编辑、删除 -->
        <template v-slot="scope">
          <el-button @click="editStudent(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteStudent(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加/编辑学生对话框 -->
    <el-dialog v-model="dialogVisible" title="学生表单">
      <el-form :model="studentForm" :rules="rules" ref="studentFormRef">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="studentForm.name"></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="code">
          <el-input v-model="studentForm.code"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="studentForm.password"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="classId">
          <el-select v-model="studentForm.classId" placeholder="请选择班级">
            <el-option
                v-for="classGroup in classGroups"
                :key="classGroup.id"
                :label="classGroup.name"
                :value="classGroup.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveStudent">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import studentService from '@/services/studentService';

export default {
  name: 'StudentManagement',
  data() {
    return {
      // 存储学生信息
      students: [],
      // 存储班级信息
      classGroups: [],
      // 学生表单数据
      studentForm: {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        classId: ''
      },
      // 对话框可见性控制
      dialogVisible: false,
      // 搜索关键字
      search: '',
      // 表单验证规则
      rules: {
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        code: [{required: true, message: '请输入学号', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        classId: [{required: true, message: '请选择班级', trigger: 'change'}]
      }
    };
  },
  methods: {
    // 获取学生信息
    fetchStudents() {
      studentService.fetchStudents()
          .then(response => {
            this.students = response.data.map(item => ({
              id: item.student.id,
              name: item.student.name,
              code: item.student.code,
              password: item.student.password, // 确保包含密码
              classId: item.student.classId,
              className: item.classGroup.name,
              specialityName: item.speciality.name,
              collegeName: item.college.name
            }));
          })
          .catch(error => {
            console.error('获取学生信息时出错:', error);
          });
    },
    // 获取班级信息
    fetchClassGroups() {
      studentService.fetchClassGroups()
          .then(response => {
            this.classGroups = response.data;
          })
          .catch(error => {
            console.error('获取班级信息时出错:', error);
          });
    },
    // 打开添加学生对话框
    openAddStudentDialog() {
      this.studentForm = {
        id: null,
        name: '',
        code: '',
        password: '123456789', // 默认密码
        classId: ''
      };
      this.dialogVisible = true;
    },
    // 编辑学生信息
    editStudent(student) {
      this.studentForm = {...student, password: ''}; // 清除密码字段
      this.dialogVisible = true;
    },
    // 保存学生信息
    saveStudent() {
      this.$refs.studentFormRef.validate((valid) => {
        if (valid) {
          studentService.saveStudent(this.studentForm)
              .then(() => {
                this.$message.success('学生信息保存成功');
                this.dialogVisible = false;
                this.fetchStudents(); // 更新成功后刷新数据
              })
              .catch(error => {
                console.error('保存学生信息时出错:', error);
                this.$message.error('保存学生信息时发生错误');
              });
        } else {
          this.$message.error('请填写完整信息');
          return false;
        }
      });
    },
    // 删除学生信息
    deleteStudent(id) {
      this.$confirm('此操作将永久删除该学生, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        studentService.deleteStudent(id)
            .then(() => {
              this.$message.success('学生信息删除成功');
              this.fetchStudents(); // 删除成功后刷新数据
            })
            .catch(error => {
              console.error('删除学生信息时出错:', error);
              this.$message.error('删除学生信息时发生错误');
            });
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    }
  },
  mounted() {
    // 组件挂载时获取学生和班级信息
    this.fetchStudents();
    this.fetchClassGroups();
  }
};
</script>

<style scoped>
.student-management {
  padding: 20px;
}
</style>
