<template>
  <div class="dashboard">
    <div class="card chart" id="bar"></div>
    <div class="card chart" id="line"></div>
    <div class="card chart" id="pie"></div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import * as echarts from "echarts";
import request from "@/utils/request.js";

const barOption = {
  title: { text: "Number of Employees by Department", left: "center" },
  tooltip: {},
  legend: { data: ["Headcount"], top: 30, right: 10 },
  grid: { top: 60, left: 40, right: 20, bottom: 50 },
  xAxis: {
    data: [],
    axisLabel: {
      interval: 0,
      rotate: 20
    }
  },
  yAxis: {},
  series: [
    {
      name: "Headcount",
      type: "bar",
      data: [],
      itemStyle: {
        color: function (params) {
          let colors = [
            "#5470c6",
            "#91cc75",
            "#fac858",
            "#ee6666",
            "#73c0de",
            "#3ba272",
            "#fc8452",
            "#9a60b4",
            "#ea7ccc",
          ];
          return colors[params.dataIndex % colors.length];
        },
      },
    },
  ],
};

const lineOption = {
  title: { text: "Number of articles published in the last 7 days", left: "center" },
  tooltip: {},
  legend: { top: 30, right: 10 },
  grid: { top: 60, left: 40, right: 20, bottom: 30 },
  xAxis: { data: [] },
  yAxis: {},
  series: [
    {
      name: "Number of articles having been published",
      type: "line",
      data: [],
      smooth: true,
    },
  ],
};

const pieOption = {
  title: { text: "The proportion of employees in each department", left: "center" },
  tooltip: { trigger: "item" },
  legend: { orient: "vertical", left: 10, top: 60 },
  series: [
    {
      name: "Employee Count",
      type: "pie",
      radius: "55%",
      center: ["50%", "60%"],
      data: [],
      label: { formatter: "{b}: {@2012} ({d}%)" },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: "rgba(0,0,0,0.5)",
        },
      },
    },
  ],
};

//document: 代表整个 HTML 文档对象。  onMounted 的作用就是确保在 ECharts 初始化图表之前，图表所需的 DOM 容器已经准备就绪。防止初始化返回null
onMounted(() => {
  const barChart = echarts.init(document.getElementById("bar"));
  request.get("/barData").then((res) => {
    barOption.xAxis.data = res.data.department;
    barOption.series[0].data = res.data.count;
    barChart.setOption(barOption);
  });

  const lineChart = echarts.init(document.getElementById("line"));
  request.get("/lineData").then((res) => {
    lineOption.xAxis.data = res.data.date;
    lineOption.series[0].data = res.data.count;
    lineChart.setOption(lineOption);
  });

  const pieChart = echarts.init(document.getElementById("pie"));
  request.get("/pieData").then((res) => {
    pieOption.series[0].data = res.data;
    pieChart.setOption(pieOption);
  });
});
</script>

<style scoped>
.dashboard {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 16px;
}

.card.chart {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  padding: 20px;
  height: 500px;
  width: 100%;
  min-width: 0;
  box-sizing: border-box;
}
</style>
