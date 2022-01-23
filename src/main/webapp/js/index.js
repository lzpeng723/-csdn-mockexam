const App = {
    data() {
        return {
            timestamp: null,
            fullscreenLoading: false,
            viewDbUrl: '#',
            fileData: {
                parentFile: '',
                files: []
            },
            currentFileName: '',
            currentFileContent: '',
            svg: `
                <path class="path" d="
                  M 30 15
                  L 28 17
                  M 25.61 25.61
                  A 15 15, 0, 0, 1, 15 30
                  A 15 15, 0, 1, 1, 27.99 7.5
                  L 15 15
                " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
              `
        }
    },
    async created() {
        this.viewDbUrl = `${location.protocol}//${location.hostname}:9090/?server=db&username=c4exam&db=c4exam&select=file_encryption`
        this.getHomeData()
    },
    methods: {
        /**
         *
         * 获取对象类型
         */
        type(obj) {
            return Object.prototype.toString.call(obj).slice(8, -1)
        },
        /**
         * 请求数据
         */
        async request(url, successFn, errorFn) {
            const startTime = new Date().getTime()
            try {
                const response = await axios.post(`${baseUrl}${url}`)
                const res = response.data
                if (res.success) {
                    ElementPlus.ElNotification({
                        title: 'Success',
                        message: `${res.msg} 耗时 ${new Date().getTime() - startTime}ms`,
                        type: 'success'
                    })
                    successFn && successFn(res.data)
                } else {
                    ElementPlus.ElNotification({
                        title: 'Error',
                        message: `${res.msg} 耗时 ${new Date().getTime() - startTime}ms`,
                        type: 'error'
                    })
                    errorFn && errorFn(res.data)
                }
            } catch (error) {
                ElementPlus.ElNotification({
                    title: `${error.name}: ${error.message}`,
                    message: `耗时 ${new Date().getTime() - startTime}ms`,
                    type: 'error'
                })
                console.error(error)
                errorFn && errorFn(error)
            }
        },
        /**
         *
         * 获取首页数据
         */
        async getHomeData() {
            this.fullscreenLoading = true
            this.request('/csdn/c4/listFile', data => this.fileData = data)
            this.fullscreenLoading = false
        },
        /**
         *
         * 搜索文件数据
         */
        async searchFileData(fileName) {
            if (fileName) {
                this.currentFileName = fileName
            }
            this.request(`/csdn/c4/getFile?fileName=${this.currentFileName}`, data => this.currentFileContent = data, err => this.currentFileContent = '')
        },

        /**
         *
         * 刷新数据
         */
        async refreshData() {
            this.request('/csdn/c4/refreshData', data => this.getHomeData())
        }
    }
}
const app = Vue.createApp(App)
app.use(ElementPlus)
app.mount("#app")