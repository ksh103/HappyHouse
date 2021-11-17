<template>
  <!-- block Header -->
  <div class="block-header py-4 py-lg-5">
    <div class="container">
      <div class="row mb-4">
        <div class="col">
          <ul class="breadcrumb bg-transparent mb-0">
            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
            <li class="breadcrumb-item active">공지사항</li>
          </ul>
        </div>
      </div>
      <!-- .row end-->
      <div class="row">
        <div class="col-xl-8 col-lg-7 col-md-12">
          <div class="welcome-text mb-4">
            <h1 class="fs-3 mb-1">{{ name }}</h1>
            <p class="fs-6 text-muted">{{ desc }}</p>
          </div>
        </div>
        <div class="col-xl-4 col-lg-5 col-md-12 text-md-end">
          <div class="d-flex justify-content-lg-end">
            <div class="">
              <button class="btn btn-light">
                <i class="fa fa-comments"></i>
              </button>
              <button class="btn btn-light">
                <i class="fa fa-area-chart"></i>
              </button>
              <button class="btn btn-light">
                <i class="fa fa-download"></i>
              </button>
              <a href="#" class="btn btn-dark">Add New</a>
            </div>
          </div>
        </div>
      </div>
      <!-- .row end-->
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  props: ['name', 'desc']
}
</script>

<style>

</style>