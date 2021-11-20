<template>
  <div>
    <BasicHeader name="거래가 조회" />
    <div id="map" style="width: 100%; height: 1200px;"></div>
  </div>
</template>

<script>
import BasicHeader from '@/components/layout/BasicHeader.vue';

export default {
  name: 'DealInfo',
  data() {
    return {
      map: '',
    }
  },
  components: {
    BasicHeader
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
          };

      this.map = new kakao.maps.Map(mapContainer, mapOption);
      console.log(this.map)
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8abe79c672729ad9f23c1abc169bb162';
      document.head.appendChild(script);
    }
  },
}
</script>

<style>

</style>