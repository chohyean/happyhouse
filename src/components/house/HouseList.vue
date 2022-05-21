<template>
	<div>
		<b-container v-if="houses && houses.length != 0">
			<b-row class="house-title-row">
				<b-col> 법정동 </b-col>
				<b-col> 아파트이름 </b-col>
				<b-col> 건축년도 </b-col>
				<b-col> 최근거래금액 </b-col>
			</b-row>
			<house-list-item v-for="(house, index) in houses" :key="index" :house="house" />
		</b-container>
		<b-container v-else>
			<b-row>
				<b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
			</b-row>
		</b-container>
	</div>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";
const houseStore = "houseStore";
export default {
	name: "HouseList",
	components: {
		HouseListItem,
	},
	data() {
		return {};
	},
	computed: {
		...mapState(houseStore, ["houses"]),
	},
};
</script>

<style scoped></style>
