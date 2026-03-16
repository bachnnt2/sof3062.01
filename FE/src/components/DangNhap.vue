<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../service/api';
const name = ref('')
const password = ref('')
const router = useRouter();
const dangnhap = async () => {
    try {
        console.log("Tên của mày là " + name.value + " và mật khẩu của mày là " + password.value)
        // import thư viện axios để call tới backend
        // cái await ở dòng dưới có nghĩa là xử lý bất đồng bộ (có nghĩa là chạy code theo thứ tự từng dòng một)
        // Nếu như không có await, thì những dòng code phía dưới cứ chạy trong khi cái dòng có chữ await chưa chạy xong
        // const response = await api.post('/api/auth/login', {
        //     "username": name.value,
        //     "password": password.value
        // })
        const response = await api.get('/api/products')
        alert("Dữ liệu nhận được từ database là " + JSON.stringify(response.data))
        // Tiếp tục hiển thị tất cả dữ liệu nhận được từ db ra ngoài màn hình (5đ)
    }
    catch (e) {
        console.log(e)
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
            </div>
        </div>
    </div>
</template>
