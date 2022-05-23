<template>
	<b-container>
		<b-row class="mb-2" align-h="center">
			<b-col class="text-right" cols="5">
				<b-input-group class="mt-4 mb-3">
					<b-form-input v-model="search"></b-form-input>
					<template #append>
						<b-input-group-text @click="searchApt()"
							><b-icon icon="search"></b-icon
						></b-input-group-text>
					</template>
				</b-input-group>
			</b-col>
		</b-row>
		<div v-if="houses && houses.length != 0">
			<b-table
				hover
				responsive
				id="house-table"
				:items="houses"
				:fields="fields"
				:per-page="perPage"
				:current-page="currentPage"
				select-mode="single"
				selectable
				@row-selected="onRowSelected"
			>
				<template #cell(dongName)="data">
					{{ data.item.dongName }}
				</template>
				<template #cell(aptName)="data">
					{{ data.item.aptName }}
				</template>
				<template #cell(buildYear)="data">
					{{ data.item.buildYear }}
				</template>
				<template #cell(recentPrice)="data">
					{{ parseInt(data.item.recentPrice.replace(",", "")) | price }}만원
				</template>
			</b-table>
		</div>
		<div v-else>
			<b-row>
				<b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
			</b-row>
		</div>
		<b-row>
			<b-col cols="3"></b-col>
			<b-col>
				<b-pagination
					class="customPagination"
					v-model="currentPage"
					:total-rows="houses.length"
					:per-page="perPage"
					aria-controls="house-table"
				></b-pagination>
			</b-col>
			<b-col cols="3"></b-col>
		</b-row>
		<div class="contact-card">
			<b-modal id="detailModal" title="연락처 상세" hide-footer>
				<b-card>
					<template #header>
						<h4 class="mb-0">{{ housedetail.aptName }}</h4>
					</template>

					<b-list-group flush>
						<b-list-group-item>{{ housedetail.buildYear }}</b-list-group-item>
						<b-list-group-item>{{ housedetail.recentPrice }}</b-list-group-item>
					</b-list-group>
				</b-card>
			</b-modal>
		</div>
	</b-container>
</template>

<script>
// import HouseListItem from "@/components/house/HouseListItem.vue";
import { houseDealSearchList, houseDetailshow } from "@/api/house.js";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";
export default {
	name: "HouseList",
	components: {
		// HouseListItem,
	},
	data() {
		return {
			house: Object,
			fields: [
				{ key: "dongName", label: "법정동" },
				{ key: "aptName", label: "아파트이름" },
				{ key: "buildYear", label: "건축년도", sortable: true },
				{ key: "recentPrice", label: "최근거래금액", sortable: true },
			],
			rows: "",
			perPage: 8,
			currentPage: 1,
			search: "",
			housedetail: {},
		};
	},
	computed: {
		...mapState(houseStore, ["houses"]),
	},
	methods: {
		...mapActions(houseStore, ["HouseDealList"]),
		async onRowSelected(items) {
			//console.log(items);
			let param = { dong: items[0].dongCode };
			houseDetailshow(
				param,

				(response) => {
					console.log(response);
					this.housedetail = items[0];
					this.$bvModal.show("detailModal");
				},
				(error) => {
					console.log(error);
				}
			);
		},

		searchApt() {
			houseDealSearchList(
				{ aptName: this.search },
				(response) => {
					console.log(response);
				},
				(error) => {
					console.log(error);
				}
			);
		},
	},
	filters: {
		price(value) {
			if (!value) return value;
			return value.toString().replace(/\B(?=(\d{4})+(?!\d))/g, "억");
		},
	},
};
</script>

<style scoped></style>
