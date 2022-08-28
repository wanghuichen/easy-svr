<template xmlns:c="http://www.w3.org/1999/html">
  <div class="box-main2 box-heigt100">
    <div class="bread4">
      <el-breadcrumb separator="&gt;">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">用户管理</a></el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="box-search">
      <el-form class="el-form el-form--inline" >
        <label for="username" style="font-weight: bold" class="el-form-item__label">用户账号</label>
        <div class="el-form-item__label">
          <el-input type="text" size="small" placeholder="请输入用户账号" @keydown.native.enter="clkBtnSearch" @clear="clkBtnSearch" clearable v-model="searchInfo.username"></el-input>
        </div>
        <label for="createTime" style="font-weight: bold" class="el-form-item__label">创建日期</label>
        <div class="el-form-item__label">
          <el-date-picker
            v-model="searchInfo.createTime"
            type="date"
            size="small"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择新建日期">
          </el-date-picker>
        </div>
        <el-button type="primary" size="small" @click="clkBtnSearch" icon="el-icon-search">查询</el-button>
        <el-button type="info" size="small" @click="clkBtnReset" icon="el-icon-refresh-right">重置</el-button>
      </el-form>
    </div>
    <div class="box-search2">
      <el-form class="el-form el-form--inline" >
        <div class="el-form-item__label">
          <el-button type="primary" size="small" @click="clkBtnAdd" plain icon="el-icon-plus">新增</el-button>
        </div>
        <div class="el-form-item__label">
          <el-button type="danger" size="small" plain icon="el-icon-delete" @click="clkBtnDelete('all')" :disabled="delDisabled">删除</el-button>
        </div>
        <div class="el-form-item__label">
            <el-upload
              class="upload-demo"
              :action="importUrl"
              :on-success="uploadSuccess"
              accept=".xlsx"
              :show-file-list="false"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
            >
              <el-button type="success" plain icon="el-icon-download" size="small">导入</el-button>
            </el-upload>

        </div>

        <div class="el-form-item__label">
            <el-button type="warning" size="small" plain icon="el-icon-upload2" @click="exportExcel">导出</el-button>
        </div>
      </el-form>
    </div>
    <div class="box-table2">
      <el-table
        v-loading="sysUserLoading"
        :data="sysUserPage.list"
        :row-style="{height: '5px'}"
        :cell-style="{padding: '5px 0'}"
        stripe
        :header-cell-style="{ 'background-color': '#f5f7fa', color: '#909399' }"
        @selection-change="sysUserPageSelectionChangeHandle"
      >
        <el-table-column type="selection" header-align="index" align="center" width="50"></el-table-column>
        <el-table-column type="index" label="序号" width="50"></el-table-column>
        <el-table-column label="用户账号" prop="username"></el-table-column>
        <el-table-column label="真实姓名" prop="realName" ></el-table-column>
        <el-table-column label="用户性别" :show-overflow-tooltip="true">
          <template slot-scope="scope">
            <span>{{scope.row.gender|filterGender}}</span>
          </template>
        </el-table-column>
        <el-table-column label="电子邮箱" :show-overflow-tooltip="true" prop="email" ></el-table-column>
        <el-table-column label="手机号码" :show-overflow-tooltip="true" prop="mobile"></el-table-column>
        <el-table-column label="创建时间" prop="createTime">
          <template slot-scope="scope">
            <span>{{scope.row.createTime|filterDateFormat1}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="220">
          <template slot-scope="scope">
            <el-button type="text" icon="el-icon-edit" size="small">修改</el-button>
            <el-button type="text" icon="el-icon-delete" @click="clkBtnDelete(scope.row)" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="box-page1">
      <el-pagination
        :current-page="sysUserPage.pageNum"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="10"
        :total="sysUserPage.total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="pageSizeChangeHandle"
        @current-change="pageCurrentChangeHandle">
      </el-pagination>
    </div>

    <el-dialog :visible.sync="visible" width="780px" title="新增/编辑">
      <el-form :model="sysUser" :rules="rules" ref="sysUserForm"  label-width="120px">
        <el-form-item style="font-weight: bold" label="用户账号" prop="username">
          <el-input type="text" size="small" placeholder="请输入用户账号" v-model="sysUser.username"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item style="font-weight: bold" label="真实姓名" prop="realName">
              <el-input type="text" size="small" v-model="sysUser.realName" placeholder="请输入用户真实姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item style="font-weight: bold" label="性别">
              <el-radio-group v-model="sysUser.gender">
                <el-radio :label="1">男</el-radio>
                <el-radio :label="2">女</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item style="font-weight: bold" label="电子邮箱">
          <el-input type="text" size="small"  v-model="sysUser.email" placeholder="请输入电子邮箱" />
        </el-form-item>
        <el-form-item style="font-weight: bold" label="手机号码">
          <el-input type="text" size="small"  v-model="sysUser.mobile" placeholder="请输入手机号码" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="clkBtnSave">确定</el-button>
        <el-button type="warning" size="mini" @click="visible = false">取消</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<script>
import request from "@/common/utils/request";
import $ from 'jquery'
export default {
  computed: {},
  inject: ["reload"],
  data() {
    return {
      searchInfo: {username: '', createTime: ''},
      sysUserPage: {pageNum: 1, pageSize: 10, list: []},
      visible: false,
      sysUser: {gender: 1},
      rules: {
        username: [
          { required: true, message: "用户账号不允许为空", trigger: "blur" }
        ],
        realName: [
          { required: true, message: "真实姓名不允许为空", trigger: "blur" }
        ],
      },
      sysUserLoading: false,
      importUrl: this.settings.apiUrl+'/sys/user/import_excel',
      delDisabled: true,
      delSysUser: [],
    };
  },
  watch: {
    "$route.path": {
      handler: function (newVal) {
        if (newVal == "/sys-user-list") {
          this.initData();
        }
      },
      immediate: true,
    },
  },
  mounted() {
    this.initData();
  },
  methods: {
    initData() {
      this.getSysUserList();
    },
    sysUserPageSelectionChangeHandle(val) {
      this.delDisabled = true;
      this.delSysUser = val;
      if (val.length >= 1) {
        this.delDisabled = false;
      } else {
        this.delDisabled = true;
      }
    },
    exportExcel() {
      window.open(this.settings.apiUrl + "/sys/user/export_excel")
    },
    getSysUserList() {
      let url = this.settings.apiUrl + "/sys/user/page";
      let d1r = {pageNum: this.sysUserPage.pageNum, pageSize: this.sysUserPage.pageSize,
      username: this.searchInfo.username, createTime: this.searchInfo.createTime};
      this.sysUserLoading = true;
      console.log(d1r)
      request.post(url, d1r).then((res) => {
        if (res.data.code == 0) {
          this.sysUserPage = res.data.data;
          this.sysUserLoading = false;
        }
      })
    },
    uploadSuccess(val) {
      if (val.code == 0) {
        this.$message.success("导入成功~");
        this.getSysUserList();
      }
    },
    handleExceed(val) {

    },
    clkBtnDelete(val) {
      this.$confirm('您确认要删除吗', '提示').then((yes) => {
        if (yes) {
          let delSysUserIds = [];
          this.delSysUser.map(sysUser => {
            delSysUserIds.push(sysUser.id);
          })
          if (val != 'all') {
            delSysUserIds.push(val.id);
          }

          let url = this.settings.apiUrl + "/sys/user/batch_delete_user";
          let data = {delUserIds: delSysUserIds};
          request.post(url, data).then((res) => {
            if (res.data.code == 0) {
              this.$message.success("删除成功~");
              this.getSysUserList();
            } else {
              this.$message.error(res.data.msg);
            }
          })
        }
      })

    },
    clkBtnSave() {
      this.$refs.sysUserForm.validate(val => {
        if (val) {
          let url = this.settings.apiUrl + "/sys/user/insert";
          let d1r = this.sysUser;
          request.post(url, d1r).then((res) => {
            if (res.data.code == 0) {
              this.$message.success("保存成功~");
              this.visible = false;
              this.getSysUserList();
            } else {
              this.$message.warning(res.data.msg)
            }
          })
        }
      })
    },
    clkBtnDetail(row) {
      this.$router.push({path: "platform-detail", query: {id: row.id}});
    },
    clkBtnReset() {
      this.searchInfo = {};
      this.sysUserPage = {pageNum: 1, pageSize: 10, list: []};
      this.getSysUserList();
    },
    pageSizeChangeHandle(val) {
      this.sysUserPage.pageSize = val;
      this.getSysUserList();
    },
    pageCurrentChangeHandle(val) {
      this.sysUserPage.pageNum = val;
      this.getSysUserList();
    },
    clkBtnSearch() {
      this.getSysUserList();
    },
    clkBtnAdd() {
      this.sysUser = {gender: 1};
      this.visible = true;
    },
  },
  filters: {
    filterGender(val) {
      if (val == 1) {
        return "男";
      } else {
        return "女";
      }
    },
    filterDateFormat1(srcVal){
      if(!srcVal){
        return "";
      }

      return srcVal.substr(0,19).replace("T", " ");
    },
  },
};
</script>

<style lang="scss" scoped="scoped">
.box-main2 {
  height: auto;
  img{
    width: 100px;
  }
  width: 98%;
  .box-table2 {
    margin: 10px;
    height: auto;
  }
  .box-search {
    display: flex;
    margin: 10px;
    .m1 {
      padding-left: 10px;
    }
  }
  .box-search2{
    margin: 10px;
  }
  .box-page1 {
    margin-top: 10px;
  }
  .bread4{
    padding: 10px 0 10px 20px;
    background-color: #eceeef;
  }
}
</style>
