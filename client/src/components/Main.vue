<template>
  <div>
    <Header name="홈!!" desc="Welcome back to your dashboard, if need a help Contact us." />
    <!-- Main Content -->
    <div class="body_area py-4 py-md-5">
      <div class="container">
        <div class="row g-3">
          <div class="col-lg-4 col-md-12">
            <h4>Planned</h4>
            <div class="dd planned_task" data-plugin="nestable">
              <ol class="dd-list">
                <li class="dd-item card p-3" data-id="1">
                  <div class="dd-handle">
                    <h6>Dashbaord</h6>
                    <span class="text-muted"
                      >Lorem Ipsum is simply dummy text of the printing and
                      typesetting industry.</span
                    >
                    <ul class="list-unstyled avatars d-flex mt-3">
                      <li class="me-3">
                        <small class="color-400">Team</small>
                      </li>
                      <!-- <li><img class="avatar sm rounded-circle border" src="../../assets/images/xs/avatar1.jpg" alt=""></li>
                                        <li><img class="avatar sm rounded-circle border" src="../../assets/images/xs/avatar4.jpg" alt=""></li>
                                        <li><img class="avatar sm rounded-circle border" src="../../assets/images/xs/avatar8.jpg" alt=""></li> -->
                    </ul>
                  </div>
                </li>
                <li class="dd-item card p-3" data-id="2">
                  <div class="dd-handle">
                    <h6>New project</h6>
                    <span class="text-muted"
                      >It is a long established fact that a reader will be
                      distracted.</span
                    >
                  </div>
                </li>
                <li class="dd-item card p-3" data-id="3">
                  <div class="dd-handle">
                    <h6>Feed Details</h6>
                    <span class="text-muted"
                      >here are many variations of passages of Lorem Ipsum
                      available, but the majority have suffered.</span
                    >
                  </div>
                </li>
              </ol>
            </div>
          </div>

          <div class="col-lg-4 col-md-12">
            <h4>In progress</h4>
            <div class="dd in_progress" data-plugin="nestable">
              <ol class="dd-list">
                <li class="dd-item card p-3" data-id="1">
                  <div class="dd-handle">
                    <h6>New Code Update</h6>
                    <span class="text-muted"
                      >Lorem Ipsum is simply dummy text of the printing and
                      typesetting industry.</span
                    >
                  </div>
                </li>
                <li class="dd-item card p-3" data-id="2">
                  <div class="dd-handle">
                    <h6>Meeting</h6>
                    <span class="text-muted"
                      >The standard chunk of Lorem Ipsum used since the 1500s is
                      reproduced below for those interested. Sections 1.10.32
                      and 1.10.33 from "de Finibus Bonorum et Malorum" by
                      Cicero</span
                    >
                    <ul class="list-unstyled avatars d-flex mt-3">
                      <li class="me-3">
                        <small class="color-400">Team</small>
                      </li>
                      <!-- <li><img class="avatar sm rounded-circle border" src="../../assets/images/xs/avatar5.jpg" alt=""></li>
                                        <li><img class="avatar sm rounded-circle border" src="../../assets/images/xs/avatar6.jpg" alt=""></li> -->
                    </ul>
                  </div>
                </li>
              </ol>
            </div>
          </div>

          <div class="col-lg-4 col-md-12">
            <h4>Completed</h4>
            <div class="dd completed_task" data-plugin="nestable">
              <ol class="dd-list">
                <li class="dd-item card p-3" data-id="1">
                  <div class="dd-handle">
                    <h6>Job title</h6>
                    <span class="text-muted"
                      >If you are going to use a passage of Lorem Ipsum, you
                      need to be sure there isn't anything embarrassing hidden
                      in the middle of text.</span
                    >
                    <ul class="list-unstyled avatars d-flex mt-3">
                      <li class="me-3">
                        <small class="color-400">Team</small>
                      </li>
                    </ul>
                  </div>
                </li>
                <li class="dd-item card p-3" data-id="2">
                  <div class="dd-handle">
                    <h6>Event Done</h6>
                    <span class="text-muted"
                      >Contrary to popular belief, Lorem Ipsum is not simply
                      random text. It has roots in a piece of classical</span
                    >
                  </div>
                </li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header.vue';

export default {
  name: 'Main',
  components: {
    Header,
  }
}
</script>

<style>

</style>