<template>
	<div>
		<div>
			<b-alert show class="user-title"><h3>APARTMENTS</h3></b-alert>
		</div>

		<h3 class="house-map-title">MAP</h3>
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
