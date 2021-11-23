<template>
  <div>
    <BasicHeader name="매물 조회" />
    <router-view></router-view>
  </div>
</template>

<script>
import BasicHeader from '@/components/layout/BasicHeader.vue';

export default {
  name: 'HouseOnGoing',
  components: {
    BasicHeader
  }
}
</script>
