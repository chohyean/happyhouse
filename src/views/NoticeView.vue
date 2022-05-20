<template>
	<b-container class="bv-example-row mt-5 text-center">
		<b-alert show variant="dark">
			<h3>공지사항</h3>
			<hr />
			<p class="mb-0">HappyHouse 관련 주요 공지를 알려드립니다.</p>
		</b-alert>
		<router-view></router-view>
	</b-container>
</template>

<script>
export default {
	name: "VuejsNoticeView",

	data() {
		return {};
	},

	mounted() {},

	methods: {},
};
</script>

<style scoped></style>
