<template>
  <b-container class="bv-example-row mt-5 text-center">
    <b-alert show variant="dark">
      <h3>오늘의 뉴스</h3>
      <hr />
      <p class="mb-0">부동산 관련 최신 소식들을 알려드립니다.</p>
    </b-alert>
    <news-list></news-list>
  </b-container>
</template>

<script>
import NewsList from "@/components/news/NewsList.vue";

export default {
  name: "VuejsNewsView",

  data() {
    return {};
  },
  components: {
    NewsList,
  },
  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
