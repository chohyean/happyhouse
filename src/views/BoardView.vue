<template>
	<b-container class="bv-example-row mt-5 text-center">
		<b-alert show variant="dark">
			<h3>Q&A 게시판</h3>
			<hr />
			<p class="mb-0">궁금하신 점이나 의견을 남겨주세요.</p>
		</b-alert>
		<router-view></router-view>
	</b-container>
</template>

<script>
export default {
	name: "VuejsBoardView",

	data() {
		return {};
	},

	mounted() {},

	methods: {},
};
</script>

<style scoped></style>
