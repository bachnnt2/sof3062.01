<script setup>
import axios from 'axios'
import { ref } from 'vue'
const name = ref('')
const password = ref('')
const loginSuccess = ref(0)
const dangnhap = async () => {
    try {
        console.log("Tên của mày là " + name.value + " và mật khẩu của mày là " + password.value)
        // import thư viện axios để call tới backend
        // cái await ở dòng dưới có nghĩa là xử lý bất đồng bộ (có nghĩa là chạy code theo thứ tự từng dòng một)
        // Nếu như không có await, thì những dòng code phía dưới cứ chạy trong khi cái dòng có chữ await chưa chạy xong
        const response = await axios.post('/api/auth/login', {
            "username": name.value,
            "password": password.value
        })
        loginSuccess.value = 200;
        console.log(loginSuccess)
    }
    catch (e) {
        console.log(e)
        loginSuccess.value = 401
    }
}
</script>

<template>
    <div class="container h-100 w-25 d-flex justify-content-center">
        <div class="card">
            <div class="card-body">
                <div>
                    <label class="form-label fw-bold">Tên</label>
                    <input type="text" class="form-control" v-model="name" />
                </div>
                <div>
                    <label class="form-label fw-bold">Mật khẩu</label>
                    <input type="password" class="form-control" v-model="password" />
                </div>
                <div class="text-center">
                    <button class="btn btn-success mt-2" @click="dangnhap()">Đăng nhập</button>
                </div>

                <div v-if="loginSuccess == 200">
                    <h2 class="text-center text-success">Đã đăng nhập thành công, xin chào {{ name }}</h2>
                    <h3>Trạng thái là {{ loginSuccess }}</h3>
                </div>

            </div>
        </div>
    </div>
</template>
