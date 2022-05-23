<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img src="@/assets/happyhouse.png" width="40px" alt="happyhouse" />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'notice' }" class="link"
              >공지사항</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'house' }" class="link"
              >매물정보</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link"
              >게시판</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'news' }" class="link"
              >오늘의 뉴스</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center"
            ><b-avatar
              class="login-avatar"
              variant="secondary"
              size="sm"
            ></b-avatar
            >{{ userInfo.name }}님 환영합니다.</b-nav-item
          >
          <b-nav-item href="#" @click.prevent="logout()">로그아웃</b-nav-item>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'info' }" class="link"
              >회원정보</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'signIn' }" class="link"
              >로그인</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'signUp' }" class="link"
              >회원가입</router-link
            ></b-nav-item
          >
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const userStore = "userStore";

export default {
  name: "VuejsHeaderNaviBar",

  data() {
    return {};
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },

  mounted() {},

  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      alert("로그아웃되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped>
.link {
  color: slategrey;
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  color: #000000;
  font-weight: bold;
}

.login-avatar {
  background-color: #959595;
  margin-right: 5px;
}
</style>
