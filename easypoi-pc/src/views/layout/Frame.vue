<template>
  <div :class="['app-wrapper',leftMenuStatus=='open'?'left-menu-open':'left-menu-close']">
    <left-menu/>
    <div class="box-main">
      <div class="box-nav-header">
        <head-navbar/>
      </div>
      <div class="box-main-contain scrollbar1">
        <transition name="fade-transform" mode="out-in">
          <keep-alive>
            <router-view :key="routePath"/>
          </keep-alive>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
  import LeftMenu from '../components/LeftMenu';
  import HeadNavbar from '../components/HeadNavbar';
  import request from '@/common/utils/request';

  export default {
    components: {LeftMenu, HeadNavbar},
    computed:{
      leftMenuStatus() {
        return this.$store.state.leftMenuStatus;
      },
      routePath() {
        return this.$route.path;
      },
    },
    data() {
      return {

      };
    },
    watch:{
      '$route.path':{
        handler:function(newVal){
          // this._initData();
        }, immediate: true
      },
    },

    methods: {
      // _initData(){
      //   this._checkUserHasPermission();
      // },

    }

  };
</script>

<style lang="scss" scoped="scoped">
  @import "@/assets/styles/param.scss";

  .app-wrapper{
    position: relative;
    width: 100%;
    height:100%;
    &.left-menu-close{
      .box-sidebar{
        width:$leftMenuCloseWidth!important;
      }
      .box-main{
        margin-left: $leftMenuCloseWidth;
      }
    }

    .box-main{
      display: flex;
      flex-direction: column;
      position: relative;
      height: 100%;
      margin-left:$leftMenuWidth;
      transition: margin-left $transitionTime;
      -webkit-transition: margin-left $transitionTime;
      background-color: #f4f4f5;
    }

    .box-nav-header{
      flex-grow: 0;
      height: $navbarHeight;
    }
    .box-main-contain{
      flex-grow: 1;
    }

  }


</style>
