<template>
  <div>
    <BasicHeader name="공지사항" />
    <router-view></router-view>
  </div>
</template>

<script>
import BasicHeader from '@/components/layout/BasicHeader.vue';

export default {
  name: 'BoardNotice',
  components: {
    BasicHeader
  }
}
</script>
