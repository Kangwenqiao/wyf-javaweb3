# API 接口文档



## 学生相关接口

### 获取所有学生的信息

获取所有学生的信息。

**接口**：`GET /students`

```javascript
export const fetchStudents = () => {
    return apiClient.get('/students');
};
```

### 添加一个新的学生

添加一个新的学生。

**接口**：`POST /students`

**参数**：

- `studentForm` (Object): 包含新学生信息的表单对象。

```javascript
export const addStudent = (studentForm) => {
    return apiClient.post('/students', studentForm);
};
```

### 更新一个学生的信息

更新一个学生的信息。

**接口**：`PUT /students/:id`

**参数**：

- `id` (number): 学生的ID。
- `studentForm` (Object): 包含更新后学生信息的表单对象。

```javascript
export const updateStudent = (id, studentForm) => {
    return apiClient.put(`/students/${id}`, studentForm);
};
```

### 删除一个学生

删除一个学生。

**接口**：`DELETE /students/:id`

**参数**：

- `id` (number): 学生的ID。

```javascript
export const deleteStudent = (id) => {
    return apiClient.delete(`/students/${id}`);
};
```

### 根据学生代码获取学生信息

根据学生代码获取学生信息。

**接口**：`GET /students/:studentCode`

**参数**：

- `studentCode` (string): 学生的代码。

```javascript
export const fetchStudentInfo = (studentCode) => {
    return apiClient.get(`/students/${studentCode}`);
};
```

### 获取指定学生的所有成绩信息

获取指定学生的所有成绩信息。

**接口**：`GET /studentCourseScore/get/:studentId`

**参数**：

- `studentId` (number): 学生的ID。

```javascript
export const fetchStudentScores = (studentId) => {
    return apiClient.get(`/studentCourseScore/get/${studentId}`);
};
```

## 班级相关接口

### 获取所有已加入的班级的信息

获取所有已加入的班级的信息。

**接口**：`GET /class/joined/all`

```javascript
export const fetchClasses = () => {
    return apiClient.get('/class/joined/all');
};
```

### 获取所有班级的信息

获取所有班级的信息。

**接口**：`GET /class/all`

```javascript
export const fetchClassGroups = () => {
    return apiClient.get('/class/all');
};
```

### 添加一个新的班级

添加一个新的班级。

**接口**：`POST /class`

**参数**：

- `classForm` (Object): 包含新班级信息的表单对象。

```javascript
export const addClass = (classForm) => {
    return apiClient.post('/class', classForm);
};
```

### 更新一个班级的信息

更新一个班级的信息。

**接口**：`PUT /class`

**参数**：

- `classForm` (Object): 包含更新后班级信息的表单对象。

```javascript
export const updateClass = (classForm) => {
    return apiClient.put(`/class`, classForm);
};
```

### 删除一个班级

删除一个班级。

**接口**：`DELETE /class/:id`

**参数**：

- `id` (number): 班级的ID。

```javascript
export const deleteClass = (id) => {
    return apiClient.delete(`/class/${id}`);
};
```

### 获取指定班级已分配的课程信息

获取指定班级已分配的课程信息。

**接口**：`GET /classCourse/getByClassId/:classId`

**参数**：

- `classId` (number): 班级的ID。

```javascript
export const fetchAssignedCourses = (classId) => {
    return apiClient.get(`/classCourse/getByClassId/${classId}`);
};
```

### 获取所有可用的课程信息

获取所有可用的课程信息。

**接口**：`GET /courses`

```javascript
export const fetchAvailableCourses = () => {
    return apiClient.get('/courses');
};
```

### 为班级添加一门课程

为班级添加一门课程。

**接口**：`POST /classCourse/add`

**参数**：

- `courseAssignment` (Object): 包含课程分配信息的表单对象。

```javascript
export const addCourseToClass = (courseAssignment) => {
    return apiClient.post('/classCourse/add', courseAssignment);
};
```

### 从班级中移除一门课程

从班级中移除一门课程。

**接口**：`DELETE /classCourse/delete/:assignmentId`

**参数**：

- `assignmentId` (number): 课程分配的ID。

```javascript
export const removeCourseFromClass = (assignmentId) => {
    return apiClient.delete(`/classCourse/delete/${assignmentId}`);
};
```

## 系主任相关接口

### 获取所有系主任的信息

获取所有系主任的信息。

**接口**：`GET /dean/all`

```javascript
export const fetchDeans = () => {
    return apiClient.get('/dean/all');
};
```

### 添加一个新的系主任

添加一个新的系主任。

**接口**：`POST /dean/add`

**参数**：

- `deanForm` (Object): 包含新系主任信息的表单对象。

```javascript
export const addDean = (deanForm) => {
    return apiClient.post('/dean/add', deanForm);
};
```

### 更新一个系主任的信息

更新一个系主任的信息。

**接口**：`PUT /dean/update`

**参数**：

- `deanForm` (Object): 包含更新后系主任信息的表单对象。

```javascript
export const updateDean = (deanForm) => {
    return apiClient.put(`/dean/update`, deanForm);
};
```

### 删除一个系主任

删除一个系主任。

**接口**：`DELETE /dean/delete/:id`

**参数**：

- `id` (number): 系主任的ID。

```javascript
export const deleteDean = (id) => {
    return apiClient.delete(`/dean/delete/${id}`);
};
```

## 教师相关接口

### 获取所有教师的信息

获取所有教师的信息。

**接口**：`GET /teacher/all`

```javascript
export const fetchTeachers = () => {
    return apiClient.get('/teacher/all');
};
```

### 添加一个新的教师

添加一个新的教师。

**接口**：`POST /teacher/add`

**参数**：

- `teacherForm` (Object): 包含新教师信息的表单对象。

```javascript
export const addTeacher = (teacherForm) => {
    return apiClient.post('/teacher/add', teacherForm);
};
```

### 更新一个教师的信息

更新一个教师的信息。

**接口**：`PUT /teacher/update`

**参数**：

- `teacherForm` (Object): 包含更新后教师信息的表单对象。

```javascript
export const updateTeacher = (teacherForm) => {
    return apiClient.put(`/teacher/update`, teacherForm);
};
```

### 删除一个教师

删除一个教师。

**接口**：`DELETE /teacher/delete/:id`

**参数**：

- `id` (number): 教师的ID。

```javascript
export const deleteTeacher = (id) => {
    return apiClient.delete(`/teacher/delete/${id}`);
};
```

### 获取指定教师教授的所有课程信息

获取指定教师教授的所有课程信息。

**接口**：`GET /teacherCourse/teacher/:teacherId/courses`

**参数**：

- `teacherId` (number): 教师的ID。

```javascript
export const fetchTeacherCourses = (teacherId) => {
    return apiClient.get(`/teacherCourse/teacher/${teacherId}/courses`);
};
```

### 为教师添加一门课程

为教师添加一门课程。

**接口**：`POST /teacherCourse/add`

**参数**：

- `courseAssignment` (Object): 包含课程分配信息的表单对象。

```javascript
export const addCourseToTeacher = (courseAssignment) => {
    return apiClient.post('/teacherCourse/add', courseAssignment);
};
```

### 从教师课程列表中移除一门课程

从教师课程列表中移除一门课程。

**接口**：`DELETE /teacherCourse/delete/:assignmentId`

**参数**：

- `assignmentId` (number): 课程分配的ID。

```javascript
export const removeCourseFromTeacher = (assignmentId) => {
    return apiClient.delete(`/teacherCourse/delete/${assignmentId}`);
};
```

### 根据教师代码获取教师信息

根据教师代码获取教师信息。

**接口**：`GET /teacher/getByCode/:teacherCode`

**参数**：

- `teacherCode` (string): 教师的代码。

```javascript
export const fetchTeacherByCode = (teacherCode) => {
    return apiClient.get(`/teacher/getByCode/${teacherCode}`);
};
```

## 学院相关接口

### 获取所有学院的信息

获取所有学院的信息。

**接口**：`GET /college/all`

```javascript
export const fetchColleges = () => {
    return apiClient.get('/college/all');
};
```

### 根据学院ID获取学院信息



根据学院ID获取学院信息。

**接口**：`GET /college/get/:collegeId`

**参数**：

- `collegeId` (number): 学院的ID。

```javascript
export const fetchCollegeById = (collegeId) => {
    return apiClient.get(`/college/get/${collegeId}`);
};
```

## 专业相关接口

### 获取所有专业信息

获取所有专业信息。

**接口**：`GET /speciality/all`

```javascript
export const fetchSpecialities = () => {
    return apiClient.get('/speciality/all');
};
```

## 课程相关接口

### 获取所有课程的信息

获取所有课程的信息。

**接口**：`GET /courses`

```javascript
export const fetchAvailableCourses = () => {
    return apiClient.get('/courses');
};
```

### 获取指定课程的信息

获取指定课程的信息。

**接口**：`GET /courses/:courseId`

**参数**：

- `courseId` (number): 课程的ID。

```javascript
export const fetchCourses = (courseId) => {
    return apiClient.get(`/courses/${courseId}`);
};
```

## 学期相关接口

### 获取指定学期的信息

获取指定学期的信息。

**接口**：`GET /terms/:termId`

**参数**：

- `termId` (number): 学期的ID。

```javascript
export const fetchTerms = (termId) => {
    return apiClient.get(`/terms/${termId}`);
};
```

## 用户相关接口

### 用户登录

用户登录。

**接口**：`POST /login`

**参数**：

- `loginForm` (Object): 包含登录信息的表单对象。

```javascript
export const loginUser = (loginForm) => {
    return apiClient.post('/login', loginForm);
};
```

### 用户登出

用户登出。

**接口**：`GET /logout`

```javascript
export const logoutUser = () => {
    return apiClient.get('/logout');
};
```