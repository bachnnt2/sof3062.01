<script setup>
import { ref } from 'vue'
const name = ref('')
const password = ref('')
const dangnhap = async () => {
    try {
        alert("Tên của mày là " + name.value + " và mật khẩu của mày là " + password.value)
        // import thư viện axios để call tới backend
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
