<template>
  <div>
    <StarRating v-model="rating" :read-only="true" :show-rating="false" :rounded-corners="true" :star-size="20" :star-points="[23,2, 14,17, 0,19, 10,34, 7,50, 23,43, 38,50, 36,34, 46,19, 31,17]"></StarRating>
  </div>
</template>

<script>
import StarRating from 'vue-star-rating'

export default {
  name: 'Test',
  data() {
    return {
      rating: 4,
      read: true,
    }
  },
  components: {
    StarRating
  },

}
</script>

<style>

</style>