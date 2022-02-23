import { InjectionKey } from 'vue'
import {createStore, Store} from 'vuex'

// 为 store state 声明类型
export interface State {
    count: number
}

// 定义 injection key
export const key: InjectionKey<Store<State>> = Symbol()

// 创建一个新的 store 实例
const store = createStore<State>({
    state() {
        return {
            count: 0
        }
    },
    mutations: {
        increment(state) {
            state.count++
        }
    }
})
export default store
