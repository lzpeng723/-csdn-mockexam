import path from 'path'
import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'

// vite.config.ts
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'

const pathSrc = path.resolve(__dirname, 'src')

// https://vitejs.dev/config/
// https://cn.vitejs.dev/config/
export default defineConfig(({command, mode}) => {
    const envConfig = loadEnv(mode, './');
    return {
        base: './',
        server: {
            host: envConfig.VITE_HOST,
            port: Number(envConfig.VITE_PORT),
            // 是否自动在浏览器打开
            open: true,
            // 是否开启 https
            https: false,
            // 服务端渲染
            ssr: false,
            base: envConfig.VITE_BASE_URL,
            outDir: envConfig.VITE_OUTPUT_DIR,
            define: {
                'process.env': {}
            }
        },
        build: {
            outDir: envConfig.VITE_OUTPUT_DIR
        },
        resolve: {
            alias: {
                '~/': `${pathSrc}/`,
            },
        },
        css: {
            preprocessorOptions: {
                scss: {
                    additionalData: `@use "~/styles/element/index.scss" as *;`,
                },
            },
        },
        plugins: [
            vue(),
            AutoImport({
                resolvers: [
                    ElementPlusResolver({
                        importStyle: 'sass',
                    }),
                ],
                dts: path.resolve(pathSrc, 'components.d.ts'),
            }),
            Components({
                resolvers: [
                    ElementPlusResolver({
                        importStyle: 'sass',
                    }),
                ],
                dts: path.resolve(pathSrc, 'components.d.ts'),
            }),
        ],
    }
})
