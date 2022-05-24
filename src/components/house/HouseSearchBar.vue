<template>
  <div>
    <b-row class="mt-4 mb-4 text-center">
      <b-col class="sm-1">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-1">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-1">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="sm-1">
        <div id="map"></div>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      map: null,
      place: null,
      geocoder: null,
      infoWindow: null,
      markers: [],
      customOverlays: [], //마커 클릭하면 그 위에 정보창? 뜨는거
      overlayIdx: 0,
    };
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.Sido();
  },
  methods: {
    ...mapActions(houseStore, ["Sido", "Gugun", "Dong", "HouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.Gugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.Dong(this.gugunCode);
    },
    async searchApt() {
      if (this.dongCode) {
        await this.HouseList(this.dongCode);
        this.displayMarkers(this.houses);
      }
    },
    initMap() {
      let mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(37.564343, 126.947613), // 처음 지도 좌표
          level: 3, // 지도의 확대 레벨
        };
      mapContainer.style.height = "500px";
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.place = new kakao.maps.services.Places();
      this.infoWindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.geocoder = new kakao.maps.services.Geocoder();
    },
    addScript() {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=070a3160964fea1eeeda8858dac29a89&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    },
    displayMarkers(places) {
      let fragment = document.createDocumentFragment();
      let bounds = new kakao.maps.LatLngBounds(); // 지도 재설정 범위정보 갖는 객체
      this.removeMarker(); //마커 제거

      for (var i = 0; i < places.length; i++) {
        let placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
        let marker = this.addMarker(placePosition, i);
        let searched = this.getListItem(i, places[i]); // 검색 결과 아파트들 넣기
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        bounds.extend(placePosition); //LanLangBounds 객체에 좌표 추가
        // mouseover 시 인포윈도우 out 시에 닫기
        (function (
          map,
          marker,
          title,
          code,
          place,
          displayInfowindow,
          removeInfowindow,
          showDetail
        ) {
          displayInfowindow(marker, title, place);
          console.log(title + " " + code);
          kakao.maps.event.addListener(marker, "click", function () {
            // displayInfowindow(marker, title, place);
            // console.log(title + " " + code);

            showDetail();
          });
          // kakao.maps.event.addListener(map, "click", function () {
          //   removeInfowindow();
          // });
          searched.onmouseover = function () {
            displayInfowindow(marker, title);
          };
          searched.onmouseout = function () {
            removeInfowindow();
          };
        })(
          this.map,
          marker,
          places[i].aptName,
          places[i].aptCode,
          places[i],
          this.displayInfowindow,
          this.removeInfowindow,
          this.showDetail
        );
        fragment.appendChild(searched); //덩어리 한번에 추가하기 위해(Dom 조작 횟수 최소화하기 좋다고함)
      }
      // 마커 지도 표시
      // 검색 동으로 지도 이동
      this.map.setBounds(bounds);
    },
    addMarker(position) {
      //마커 지도 위에 표시
      let imageSrc = require("@/assets/houseMarker.png"),
        //"https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", 기존꺼 우선keep

        imageSize = new kakao.maps.Size(30, 40), // 마커 이미지의 크기
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });
      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    removeMarker() {
      //지도 위에 표시되고 있는 모든 마커 제거
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    getListItem(index, place) {
      //검색결과 항목을 Element로 반환(map api 키워드 장소검색부분 참고)
      let el = document.createElement("li");
      let itemStr = `
          <span class="markerbg marker_${index + 1}></span>
          <div class="info"><h5>${place.aptName}</h5>
          <span>${place.sidoName} ${place.gugunName} ${place.dongName} ${
        place.jibun
      }</span>
          `;
      el.innerHTML = itemStr;
      el.className = "item";
      return el;
    },
    displayInfowindow(marker, title, place) {
      var content = `<div class="customoverlay" onclick="showDetail()">
                                <span class="aptInfo">
                                  <div class = "aptName">${title}</div>
                                  <div class = "aptCost">${place.recentPrice}만원</div>
                                </span>
                            </div>`;
      let position = new kakao.maps.LatLng(
        marker.getPosition().getLat(),
        marker.getPosition().getLng()
      );
      let customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        // xAnchor: 0.3,
        // yAnchor: 1.5,
        zIndex: 5,
      });
      this.customOverlays[this.overlayIdx] = customOverlay;
      this.customOverlays[this.overlayIdx++].setMap(this.map);
    },
    removeInfowindow() {
      if (this.overlayIdx > 0) {
        this.customOverlays[--this.overlayIdx].setMap(null);
      }
    },
    showDetail() {
      console.log("click");
      this.$bvModal.show("detailModal");
    },
  },
};
</script>

<style>
.customoverlay {
  position: relative;
  bottom: 75px;
  border-radius: 5px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay .aptInfo {
  display: block;
  color: #000;
  border-radius: 5px;
  overflow: hidden;
  text-align: center;
  background: #fff;
  padding: 5px 8px;
  font-size: 10px;

  z-index: 4;
}
.customoverlay .aptCost {
  color: blue;
  padding: 5px 8px;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
