<template>
	<div>
		<b-carousel
			id="carousel-1"
			v-model="slide"
			:interval="4000"
			controls
			indicators
			background="#ababab"
			img-width="400"
			img-height="200"
			style="text-shadow: 1px 1px 2px #333"
			@sliding-start="onSlideStart"
			@sliding-end="onSlideEnd"
		>
			<!-- Text slides with image -->
			<b-carousel-slide
				caption="HappyHouse에 오신 것을 환영합니다."
				:img-src="require('@/assets/apt1.jpeg')"
			></b-carousel-slide>

			<!-- Slides with custom text -->
			<b-carousel-slide caption="Welcome to HappyHouse" :img-src="require('@/assets/apt2.jpg')">
			</b-carousel-slide>

			<!-- Slides with image only -->
			<b-carousel-slide
				caption="Bienvenido a HappyHouse"
				:img-src="require('@/assets/apt3.jpg')"
			></b-carousel-slide>

			<!-- Slides with image only -->
			<b-carousel-slide
				caption="欢迎来到快乐屋"
				:img-src="require('@/assets/apt4.jpg')"
			></b-carousel-slide>
		</b-carousel>
		<home-news></home-news>
	</div>
</template>

<script>
import HomeNews from "@/components/Home/HomeNews.vue";
export default {
	name: "HomeView",
	props: {
		msg: String,
	},
	components: {
		HomeNews,
	},
	data() {
		return {
			slide: 0,
			sliding: null,
		};
	},
	methods: {
		onSlideStart() {
			this.sliding = true;
		},
		onSlideEnd() {
			this.sliding = false;
		},
	},
};
</script>

<style scoped></style>
