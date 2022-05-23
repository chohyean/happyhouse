<template>
  <div>
    <b-container class="bv-example-row mt-5 text-center">
      <b-alert show variant="dark">
        <h3>매물정보</h3>
        <hr />
        <p class="mb-0">최근 거래내역 정보를 찾아보세요.</p>
      </b-alert>
    </b-container>

    <b-row>
      <b-col cols="1"> </b-col>
      <b-col cols="5" align="left">
        <house-search-bar></house-search-bar>
      </b-col>
      <b-col cols="5" align="right" style="margin-top: 83px">
        <house-list />
      </b-col>
      <b-col cols="1"> </b-col>
    </b-row>
  </div>
</template>

<script>
import HouseList from "@/components/house/HouseList.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
export default {
  name: "HouseView",

  data() {
    return {};
  },
  components: {
    HouseList,
    HouseSearchBar,
  },
  mounted() {},

  methods: {},
};
</script>

<style scoped>
.house-map-title {
  padding-top: 10px;
  color: #8cbded;
}
</style>
