import { AuthProvider } from "./components/AuthContext"
export default function page(){
  return(
    <AuthProvider>
      <main>
      home
    </main>
    </AuthProvider>
  )
}