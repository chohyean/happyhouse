<template>
  <div>
    <div class="main main-raised">
      <div class="section">
        <div class="container">
          <b-row class="mt-4 mb-4 text-center">
            <b-col></b-col>
            <b-col class="sm-3">
              <b-input
                v-model="word"
                style="font-family: Verdana"
                @keyup.enter="searchN"
              ></b-input>
            </b-col>
            <b-col class="sm-3">
              <b-button
                squared
                style="float: left; font-family: 'IBMPlexSansKR-Regular'"
                class="md-info"
                @click="searchN"
                >검색</b-button
              >
            </b-col>
          </b-row>
          <br />
          <br />

          <section>
            <b-table
              id="newstable"
              head-variant="light"
              :items="NewsList"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
              responsive="sm"
              style="font-size: 12px; font-family: 'IBMPlexSansKR-Regular'"
            >
              <template #cell(제목)="data">
                <a
                  :href="`${data.item.url}`"
                  target="_blank"
                  style="color: darkgreen"
                  >{{ data.item.title }}</a
                >
              </template>
              <template #cell(내용)="data">
                {{ data.item.contents }}
              </template>
              <template #cell(시간)="data">
                <b-icon icon="clock" font-scale="1" variant="dark"></b-icon>
                {{ data.item.time }}
              </template>
            </b-table>
            <b-row class="mt-4" align-h="end">
              <b-col cols="4"
                ><b-pagination
                  class="customPagination"
                  v-model="currentPage"
                  :total-rows="NewsList.length"
                  :per-page="perPage"
                  aria-controls="newstable"
                ></b-pagination
              ></b-col>
              <b-col cols="4"
                ><b-button squared class="md-default" @click="all"
                  >목록</b-button
                ></b-col
              >
            </b-row>
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  data() {
    return {
      fields: ["제목", "내용", "시간"],
      currentPage: 1,
      perPage: 10,
      word: "",
    };
  },
  created() {
    this.$store.dispatch("ALLNEWS");
  },
  computed: {
    ...mapState(["NewsList"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    ...mapActions(["ALLNEWS", "SEARCHNEWS"]),
    searchN() {
      if (this.word == "") alert("검색어를 입력해주세요.");
      // else this.$store.dispatch("SEARCHNEWS", this.word);
      else this.SEARCHNEWS(this.word);
    },
    all() {
      this.word = "";
      // this.$store.dispatch("ALLNEWS");
      this.ALLNEWS();
    },
  },
};
</script>

<style scoped></style>
