<template>
<div class="container">
  <Header name="공지사항 작성" desc="Welcome back to your dashboard, if need a help Contact us." />
  <form class="row g-3 basic-form" method="post" novalidate="">
    <div class="col-md-12">
        <label class="form-label">제목 </label>
        <input v-model="title" type="text" class="form-control" required="">
    </div>
    <div class="col-12">
        <span class="float-label">
            <textarea v-model="content" class="form-control" id="TextArea" rows="5" cols="30" placeholder="Comment"></textarea>
            <label class="form-label" for="TextArea">Text Area</label>
        </span>
    </div>
    <div class="col-12">
        <button @click="boardInsert" type="submit" class="btn btn-primary">확인</button>
    </div>
</form>
</div>
</template>

<script>
import Header from '@/components/Header.vue';
import http from "@/common/axios.js";

export default {
  name: 'BoardNoticeInsert',
  components: {
    Header, 
  },
  data() {
    return {
      title: '',
      content: '',
    };
  },
  methods: {
    boardInsert(){
    var formData = new FormData();
    formData.append("title", this.title);
    formData.append("content", this.content);

    http.post(
      "/notices",
      formData,
      { headers: { 'Content-Type': 'multipart/form-data' } })
      .then(({ data }) => {
        console.log("InsertVue: data : ");
        console.log(data);
        if (data === "login") {
          this.$router.push('/user/login');
        }else{
          this.$alertify.success('글이 등록되었습니다.');
          this.$router.push('/board/notice');
        }
      })
      .catch((error) => {
        console.log("InsertVue: error ");
        console.log(error);
        if( error.response.status == '404'){
          this.$alertify.error('Opps!! 서버에 문제가 발생했습니다.');          
        }
      });
    },
  },
  mounted() {
  }
}
</script>

<style>

</style>