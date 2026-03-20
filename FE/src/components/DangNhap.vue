<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import api from '../service/api';
const name = ref('')
const password = ref('')
const router = useRouter();
const danhSachBeer = ref([]) // đây là biến hứng dữ liệu trả về từ database và dùng nó để hiển thị trên table
// const dangnhap = async () => {
//     try {
//         console.log("Tên của mày là " + name.value + " và mật khẩu của mày là " + password.value)
//         // import thư viện axios để call tới backend
//         // cái await ở dòng dưới có nghĩa là xử lý bất đồng bộ (có nghĩa là chạy code theo thứ tự từng dòng một)
//         // Nếu như không có await, thì những dòng code phía dưới cứ chạy trong khi cái dòng có chữ await chưa chạy xong
//         // const response = await api.post('/api/auth/login', {
//         //     "username": name.value,
//         //     "password": password.value
//         // })
//         const response = await api.get('/api/products')
//         alert("Dữ liệu nhận được từ database là " + JSON.stringify(response.data))
//         // Tiếp tục hiển thị tất cả dữ liệu nhận được từ db ra ngoài màn hình (5đ)
//     }
//     catch (e) {
//         console.log(e)
//     }
// }

//Trong các framework như Vue, Angular, React,... thì chữ mount thường dùng để chỉ tới hàm khởi tạo
// Tức là sẽ chạy khi component được render
onMounted(async () => {
    console.log("Component đã được tạo thành công")
    //Bắt đầu gọi API bằng Axios
    try {
        const response = await api.get('/api/products')
        //Hứng dữ liệu vào biến ở Vue, biến này sẽ được sử dụng để hiển thị ở table
        danhSachBeer.value = [...response?.data]
    }
    catch (e) {
        console.log(e)
    }
});
</script>

<template>
    <div class="container h-100">
        <table class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên beer</th>
                    <th>Ảnh beer</th>
                    <th>Giá tiền</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(doituong, vitri) in danhSachBeer">
                    <td>{{ vitri + 1 }}</td>
                    <td class="fw-bold">{{ doituong.name }}</td>
                    <td>
                        <img :src=doituong.image width="50%" height="50%" style="object-fit: cover;" />
                    </td>
                    <td>{{ doituong.price }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
