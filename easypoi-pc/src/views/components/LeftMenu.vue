<template>
  <div :class="['box-sidebar']">
    <el-menu
      :collapse="leftMenuStatus1"
      :background-color="cssParam1.bgLeftMenu"
      :text-color="cssParam1.leftMenuColor"
      :unique-opened="false"
      :active-text-color="cssParam1.leftMenuActiveColor"
      :collapse-transition="true"
      :default-active="routeMenuIndex"
      :default-openeds="menuOpeneds"
      mode="vertical"
    >
      <el-menu-item index="index" @click.native="clkItem('/index')">
        <i class="menuitem-icon el-icon-s-home"></i>
        <span class="menuitem-text" slot="title">控制台</span>
      </el-menu-item>


      <el-submenu index="sys">
        <template slot="title"><i class="el-icon-s-marketing"></i>
          <span class="menuitem-text" slot="title">系统管理</span>
        </template>


        <el-menu-item  index="sys-user-list" @click.native="clkItem('/sys-user-list')">
          <i class="menuitem-icon el-icon-user"></i>
          <span class="menuitem-text" slot="title">用户管理</span>
        </el-menu-item>

      </el-submenu>
    </el-menu>

  </div>
</template>

<script>
  import cssParam from '@/assets/styles/param.scss';
  import request from '@/common/utils/request';
  import '@/assets/icon/iconfont.css';

  export default {
    name: 'LeftMenu',
    computed: {
      cssParam1() {
        return cssParam;
      },
      leftMenuStatus1() {
        let status =  this.$store.state.leftMenuStatus === 'close';
        if(!status) {
          this.menuOpeneds = ['settings', 'settings-statist'];
        }
        return status;
      },
      routeMenuIndex(){
        if(this.$route.meta==null || this.$route.meta.leftMenuIndex == null){
          return 'index';
        }
        return this.$route.meta.leftMenuIndex;
      }
    },
    data() {
      return {
        menuOpeneds:['settings','settings-statist'],
      };
    },
    mounted() {
    },
    methods: {
      clkItem(path) {
        this.$router.push(path);
      }
    }
  };
</script>

<style lang="scss" scoped>
  @import "@/assets/styles/param.scss";

  .el-menu {
    border-right: none;
  }

  .menuitem-icon, .menuitem-text {
    transition: color 0.3s;
    -moz-transition: color 0.3s;
  }

  .box-sidebar{
    -webkit-transition: width $transitionTime;
    transition: width $transitionTime;
    width: $leftMenuWidth!important;
    background-color: $bgLeftMenu;
    height: 100%;
    position: fixed;
    font-size: 0;
    top: 0;
    bottom: 0;
    left: 0;
    z-index: 1001;
    overflow: hidden;
    .el-scrollbar{
      height: 100%;
      overflow: hidden;
    }


  }
  .el-scrollbar-leftmenu{
    .el-scrollbar__wrap{
      overflow-x: hidden;
    }
  }

</style>
