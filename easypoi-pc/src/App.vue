<template>
  <div id="app">
    <router-view v-if="isRouteAlive"></router-view>
  </div>
</template>

<script>

  export default {
    name: 'App',
    computed: {},
    watch: {},
    //加入provide
    provide() {
      return{
        //返回reload方法，该方法为全局方法
        reload: this.reload,
      }
    },
    data() {
      return {
        isRouteAlive:true
      };
    },
    methods: {
      //刷新页面
      reload() {
        this.isRouteAlive = false;
        this.$nextTick(function () {
          this.isRouteAlive = true;
        })
      }
    }
  };
</script>

<style type="text/css">
</style>
