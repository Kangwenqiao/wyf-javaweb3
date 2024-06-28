```sql
create table if not exists college
(
    id     int auto_increment comment '学院id'
        primary key,
    name   varchar(32)  not null comment '名称',
    remark varchar(256) null comment '辅助描述'
)
    comment '学院(包含教师、班级)';

create table if not exists dean
(
    id       int auto_increment comment '教务id'
        primary key,
    name     varchar(16)                     not null comment '名字',
    password varchar(32) default '123456789' not null comment '密码',
    code     varchar(16)                     not null comment '工号(登录)',
    remark   varchar(256)                    null comment '辅助描述',
    constraint dean_un
        unique (code)
)
    comment '教务(设置专业、编制班级、开设课程、组织选课等)' charset = utf8mb3;

create table if not exists speciality
(
    id         int auto_increment comment '专业id'
        primary key,
    name       varchar(32)    not null comment '名称',
    grade      year           not null comment '年级',
    code       varchar(16)    not null comment '学科代码',
    college_id int default -1 null comment '学院id',
    remark     varchar(256)   null comment '辅助描述',
    constraint speciality_ibfk_1
        foreign key (college_id) references college (id)
)
    comment '专业(比如2020级特色软件工程)' charset = utf8mb3;

create table if not exists class
(
    id            int auto_increment comment '班级id'
        primary key,
    name          varchar(32)    not null comment '名称',
    code          varchar(16)    not null comment '班级编号',
    speciality_id int default -1 null comment '专业id',
    remark        varchar(256)   null comment '辅助描述',
    constraint class_un
        unique (code),
    constraint class_ibfk_1
        foreign key (speciality_id) references speciality (id)
)
    comment '班级(比如2020级软件工程300班)';

create index speciality_id
    on class (speciality_id);

create index college_id
    on speciality (college_id);

create table if not exists student
(
    id       int auto_increment comment '学生id'
        primary key,
    name     varchar(32)                     not null comment '姓名',
    password varchar(64) default '123456789' not null comment '密码',
    code     varchar(32)                     not null comment '学号(登录)',
    class_id int         default -1          null comment '班级id',
    remark   varchar(256)                    null comment '辅助描述',
    constraint student_un
        unique (code),
    constraint student_ibfk_1
        foreign key (class_id) references class (id)
)
    comment '学生(班级决定了其专业名称、年级、学院)' charset = utf8mb3;

create index class_id
    on student (class_id);

create table if not exists teacher
(
    id         int auto_increment comment '教师id'
        primary key,
    name       varchar(32)                     not null comment '姓名',
    password   varchar(64) default '123456789' not null comment '密码',
    code       varchar(32)                     not null comment '工号(登录)',
    college_id int         default -1          null comment '学院id',
    remark     varchar(256)                    null comment '辅助描述',
    constraint teacher_un
        unique (code),
    constraint teacher_ibfk_1
        foreign key (college_id) references college (id)
)
    comment '教师(划归某个教学单位)';

create index college_id
    on teacher (college_id);

create table if not exists term
(
    id         int auto_increment comment '学期ID'
        primary key,
    name       varchar(100) not null comment '学期名称',
    start_date date         not null comment '开始日期',
    end_date   date         not null comment '结束日期',
    remark     varchar(255) null comment '备注'
)
    comment '学期';

create table if not exists course
(
    id      int auto_increment comment '课程ID'
        primary key,
    name    varchar(100) not null comment '课程名称',
    code    varchar(50)  not null comment '课程编号',
    term_id int          not null comment '学期ID',
    remark  varchar(255) null comment '备注',
    constraint code
        unique (code),
    constraint course_ibfk_1
        foreign key (term_id) references term (id)
)
    comment '课程';

create table if not exists class_course
(
    id        int auto_increment comment '关联ID'
        primary key,
    class_id  int not null comment '班级ID',
    course_id int not null comment '课程ID',
    constraint class_course_ibfk_1
        foreign key (class_id) references class (id),
    constraint class_course_ibfk_2
        foreign key (course_id) references course (id)
)
    comment '课程与班级关联';

create index class_id
    on class_course (class_id);

create index course_id
    on class_course (course_id);

create index term_id
    on course (term_id);

create table if not exists exam
(
    id        int auto_increment comment '考试ID'
        primary key,
    course_id int          not null comment '课程ID',
    date      date         not null comment '考试日期',
    location  varchar(100) not null comment '考试地点',
    remark    varchar(255) null comment '备注',
    constraint exam_ibfk_1
        foreign key (course_id) references course (id)
)
    comment '考试';

create index course_id
    on exam (course_id);

create table if not exists student_course_score
(
    id         int auto_increment comment '成绩ID'
        primary key,
    student_id int           not null comment '学生ID',
    course_id  int           not null comment '课程ID',
    score      decimal(5, 2) not null comment '成绩',
    remark     varchar(255)  null comment '备注',
    constraint student_course_score_ibfk_1
        foreign key (student_id) references student (id),
    constraint student_course_score_ibfk_2
        foreign key (course_id) references course (id)
)
    comment '成绩';

create index course_id
    on student_course_score (course_id);

create index student_id
    on student_course_score (student_id);

create table if not exists teacher_course
(
    id         int auto_increment comment '关联ID'
        primary key,
    teacher_id int not null comment '教师ID',
    course_id  int not null comment '课程ID',
    constraint teacher_course_ibfk_1
        foreign key (teacher_id) references teacher (id),
    constraint teacher_course_ibfk_2
        foreign key (course_id) references course (id)
)
    comment '课程与教师关联';

create index course_id
    on teacher_course (course_id);

create index teacher_id
    on teacher_course (teacher_id);
```