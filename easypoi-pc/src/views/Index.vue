<template>
  <div class="box-main2 box-heigt100">
    <div class="box-pie" style="height: 600px; width: 1200px" ref="chart"></div>
  </div>
</template>

<script>
  import request from '@/common/utils/request';


  export default {
    computed:{
    },
    data() {
      return {
        chart:null,
        // 配置可视化图形
        option : {
          legend: {},
          tooltip: {
            trigger: 'axis',
            showContent: false
          },
          dataset: {
            source: [
              ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
              ['Milk Tea', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
              ['Matcha Latte', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
              ['Cheese Cocoa', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
              ['Walnut Brownie', 25.2, 37.1, 41.2, 18, 33.9, 49.1]
            ]
          },
          xAxis: { type: 'category' },
          yAxis: { gridIndex: 0 },
          grid: { top: '55%' },
          series: [
            {
              type: 'line',
              smooth: true,
              seriesLayoutBy: 'row',
              emphasis: { focus: 'series' }
            },
            {
              type: 'line',
              smooth: true,
              seriesLayoutBy: 'row',
              emphasis: { focus: 'series' }
            },
            {
              type: 'line',
              smooth: true,
              seriesLayoutBy: 'row',
              emphasis: { focus: 'series' }
            },
            {
              type: 'line',
              smooth: true,
              seriesLayoutBy: 'row',
              emphasis: { focus: 'series' }
            },
            {
              type: 'pie',
              id: 'pie',
              radius: '30%',
              center: ['50%', '25%'],
              emphasis: {
                focus: 'self'
              },
              label: {
                formatter: '{b}: {@2012} ({d}%)'
              },
              encode: {
                itemName: 'product',
                value: '2012',
                tooltip: '2012'
              }
            }
          ]
        }
      };
    },
    watch:{
      '$route.path':{
        handler:function(newVal){
          if(newVal == '/index'){
            this._initData();
          }
        }, immediate: true
      },
    },
    beforeRouteLeave(to,from,next){
      // this._clearData();
      next();
    },
    mounted() {
      this._initData();
    },
    methods: {
      _initData(){
        if(this.inited){
          this.getPage();
          return;
        }
        this.inited = true;
      },
      getPage() {
        // 引用chart并初始化
        this.chart = this.$echarts.init(this.$refs.chart);
        // 使用刚指定的配置项和数据显示图表。
        this.chart.setOption(this.option);
      }

    },
    filters:{
      filterCrccGender(val){
        if(val==1){
          return '男';
        }else if(val==2){
          return '女';
        }else{
          return '';
        }
      }
    }

  };
</script>

<style lang="scss" scoped="scoped">

  .box-main2{
    display: flex;
    .box-pie{
      margin-top: 20px;
    }
  }



</style>
