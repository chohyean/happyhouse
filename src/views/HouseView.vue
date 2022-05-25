<template>
  <div>
    <div class="bv-example-row text-center">
      <div class="img">
        <b-card
          class="card text-center"
          overlay
          :img-src="require('@/assets/headerImg.jpg')"
          text-variant="dark"
        >
          <div class="card-header-title">Housedeal Info</div>
        </b-card>
      </div>
    </div>

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

<style scoped></style>
