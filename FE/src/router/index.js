import DangNhap from "../components/DangNhap.vue";
import { createRouter, createWebHistory } from "vue-router";

const routes = [
    { path: "/", redirect:"/dangnhap" },
    { path: "/dangnhap", component: DangNhap, name: "DangNhap" }
]

const router = createRouter(
    {
        history: createWebHistory(),
        routes
    }
)
export default router