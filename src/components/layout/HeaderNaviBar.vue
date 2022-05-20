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
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
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
export default {
  name: "VuejsHeaderNaviBar",

  data() {
    return {};
  },

  mounted() {},

  methods: {},
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
</style>
