<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-2" align-h="end">
        <b-col class="text-right" cols="3">
          <b-input-group class="mt-3">
            <b-form-input placeholder="검색어를 입력하세요"></b-form-input>
            <template #append>
              <b-input-group-text
                ><b-icon icon="search"></b-icon
              ></b-input-group-text>
            </template>
          </b-input-group>
        </b-col>
      </b-row>
      <b-row>
        <b-col v-if="articles.length">
          <b-table
            hover
            responsive
            id="board-table"
            :items="articles"
            :fields="fields"
            :per-page="perPage"
            :current-page="currentPage"
          >
            <template #cell(articleno)="data">
              {{ data.item.articleno }}
            </template>
            <template #cell(subject)="data">
              <router-link
                class="link"
                :to="{
                  name: 'boardDetail',
                  params: { articleno: data.item.articleno },
                }"
                >{{ data.item.subject }}</router-link
              >
            </template>
            <template #cell(userid)="data">
              {{ data.item.userid }}
            </template>
            <template #cell(hit)="data">
              {{ data.item.hit }}
            </template>
            <template #cell(regtime)="data">
              {{ data.item.regtime | dateFormat }}
            </template>
          </b-table>
        </b-col>
      </b-row>
      <b-row class="mb-1" align-h="end">
        <b-col cols="4">
          <b-pagination
            class="customPagination"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="board-table"
          ></b-pagination>
        </b-col>
        <b-col class="text-right" cols="4">
          <b-button squared @click="moveWrite()"
            ><b-icon icon="pencil"></b-icon> 글쓰기</b-button
          >
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { listArticle } from "@/api/board.js";
// import BoardListItem from "@/components/board/item/BoardListItem";
import moment from "moment";

export default {
  name: "VuejsBoardList",
  components: {
    // BoardListItem,
  },
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호" },
        { key: "subject", label: "글제목" },
        { key: "userid", label: "아이디" },
        { key: "hit", label: "조회수" },
        { key: "regtime", label: "등록일" },
      ],
      rows: "",
      perPage: 5,
      currentPage: 1,
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticle(
      param,
      (response) => {
        this.articles = response.data;
        this.rows = this.articles.length;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.link {
  color: slategrey;
  text-decoration: none;
}
a:hover {
  font-weight: bold;
}
</style>
