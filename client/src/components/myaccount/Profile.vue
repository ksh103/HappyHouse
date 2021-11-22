<template>
  <div class="body_area py-2">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">기본 정보</h1>
          <div class="card p-3">
            <div class="d-flex flex-wrap flex-md-nowrap justify-content-between">
              <div class="doctor-avatar order-0 order-md-1">
                <button @click="modifyInfo" style="font-size: 14px;" class="btn px-2 py-1 btn-animate-2 fill">정보 수정</button>
                <button @click="changePassword" style="font-size: 14px;" class="btn px-2 py-1 btn-animate-2 fill">비밀번호 변경</button>
              </div>
              <div class="doctor-detail order-1 order-md-0">
                <ul class="resume-box">
                  <li>
                    <div class="icon text-center">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="fw-bold mb-0">아이디</div>
                    <span>{{ id }}</span>
                  </li>
                  <li>
                    <div class="icon text-center">
                        <i class="fa fa-mobile"></i>
                    </div>
                    <div class="fw-bold mb-0">휴대 전화</div>
                    <span>010-9980-0000</span>
                  </li>
                  <li>
                    <div class="icon text-center">
                        <i class="fa fa-envelope"></i>
                    </div>
                    <div class="fw-bold mb-0">E-Mail</div>
                    <span>{{ email }}</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <button @click="deleteUser" style="font-size: 14px;" class="btn btn-danger mt-4 mb-4 px-2 py-1 lift float-end">회원 탈퇴</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/common/axios.js";
import { mapState, mapActions } from "vuex";

const SUCCESS = 1;
const storeName='userStore';

export default {
  name: 'Profile',
  computed: {
    ...mapState(storeName, ['id', 'password', 'email']),
  },
  methods: {
    ...mapActions(storeName, ['logout']),
    modifyInfo() {
      this.$swal({
        text: '비밀번호를 입력해 주세요.',
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        buttons: true
      }).then(value => {
        if (value) {
          if (value === this.password) {
            this.$router.push('/myaccount/modifyinfo');
          } else {
            this.$swal('비밀번호가 일치하지 않습니다.')
              .then(() => this.modifyInfo());
          }
        } else if (value == '') {
          this.$swal('비밀번호를 입력하세요!')
            .then(() => this.modifyInfo());
        }
      })
    },
    changePassword() {
      this.$swal({
        text: '비밀번호를 입력해 주세요.',
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        buttons: true
      }).then(value => {
        if (value) {
          if (value === this.password) {
            this.$router.push('/myaccount/changePw');
          } else {
            this.$swal('비밀번호가 일치하지 않습니다.')
              .then(() => this.changePassword());
          }
        } else if (value == '') {
          this.$swal('비밀번호를 입력하세요!')
            .then(() => this.changePassword());
        }
      })
    },
    deleteUser() {
      this.$swal({
        title: '정말 탈퇴하시겠습니까?',
        text: '탈퇴하시려면 비밀번호를 입력해 주세요.',
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        icon: 'warning',
        dangerMode: true,
        buttons: true
      }).then(value => {
        if (value) {
          if (value === this.password) {
            http.delete('/user')
              .then(response => {
                if (response.data.result === SUCCESS) {
                  this.$swal('탈퇴 처리가 완료되었습니다.', '지금까지 이용해주셔서 감사합니다.', { icon: 'success' })
                    .then(() => this.logout());
                } else {
                  this.$swal('처리중에 문제가 발생하였습니다.', { icon: 'error' });
                }
              })
              .catch(error => {
                console.log(error);
                this.$swal('서버에 문제가 발생하였습니다.', { icon: 'error' });
              })
          } else {
            this.$swal('비밀번호가 일치하지 않습니다.')
              .then(() => this.deleteUser());
          }
        } else if (value == '') {
          this.$swal('비밀번호를 입력하세요!')
            .then(() => this.deleteUser());
        }
      })
    }
  },
  mounted() {
    
  }
}
</script>

<style>

</style>