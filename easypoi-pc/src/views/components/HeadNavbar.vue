<template>
  <div class="box-head-navbar">
    <div class="flex01">
      <div class="box-indent-leftmenu" @click="clkToggleLeftMenu">
        <i :class="[leftMenuStatus=='open'?'el-icon-s-fold':'el-icon-s-unfold']"></i>
      </div>
      <div class="box-sitename" v-html="appName"></div>
    </div>
    <div class="flex02">
      <div class="box-username">
<!--        <el-button type="text" v-html="this.userInfo.realname"></el-button>-->
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'HeadNavbar',
    data() {
      return {
        appName: this.settings.appName,
      };
    },
    computed: {
      leftMenuStatus() {
        return this.$store.state.leftMenuStatus;
      },
    },
    mounted() {
    },
    watch: {
    },
    methods: {
      clkToggleLeftMenu() {
        this.$store.commit('doToggleLeftMenu');
      },
    }
  };
</script>

<style lang="scss" scoped>
  @import "@/assets/styles/param.scss";

  .box-head-navbar {
    height: $navbarHeight;
    background-color: $bgHeadNavbar;
    display: flex;

    .flex01 {
      flex: 1;
      display: flex;

      .box-sitename {
        line-height: $navbarHeight;
        font-size: 14px;
      }

    }

    .flex02 {
      padding-right: 20px;
      flex: 1;
      display: flex;
      justify-content: flex-end;

      .box-daping{
        cursor: pointer;
        padding-left: 20px;
        .iconfont{
          line-height: 40px;
          font-size: 22px;
          color: #67c23a;
        }
      }
      .box-change-locale-lang {
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .box-username{
        margin-left:20px;
      }
      .box-notify {
        display: flex;
        padding: 10px;
        align-items: center;

        .iconfont {
          font-size: 26px;
          color: #bfcbd9;
        }
      }

      .box-useravator {
        padding-left: 5px;
        display: flex;
        align-items: center;
      }
    }

    .box-indent-leftmenu {
      padding-left: 16px;
      padding-right: 16px;
      line-height: $navbarHeight;
      cursor: pointer;

      .iconfont {
        font-size: 22px;
        color: #333333;
      }
    }
  }
</style>
