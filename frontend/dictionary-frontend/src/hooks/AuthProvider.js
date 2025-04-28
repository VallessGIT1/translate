import {useContext, createContext, useState} from "react"
import {useNavigate} from "react-router-dom"
import {BASE_API_URL} from "../Constants"
import toast from "react-hot-toast";

const AuthContext = createContext()

const AuthProvider = ({children}) => {

  const navigate = useNavigate()

  const [user, setUser] = useState(null)
  const [token, setToken] = useState(localStorage.getItem("site") || "")

  const signInHandler = async data => {
    try {
      const response = await fetch(`${BASE_API_URL}auth/signIn`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })

      const json = await response.json()

      if (response.status === 200) {
        setToken(json.token)
        setUser(json.user)

        localStorage.setItem("site", json.token)
        navigate("/")

        toast.success("Успешная авторизация")

      } else {
        toast.error(json.message)
      }

    } catch (error) {
      toast.error("Произошла ошибка...")
      console.error(error)
    }
  }

  const signUpHandler = async data => {
    try {
      const response = await fetch(`${BASE_API_URL}auth/signUp`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })

      const json = await response.json()

      if (response.status === 200) {
        setToken(json.token)
        setUser(json.user)

        localStorage.setItem("site", json.token)
        navigate("/")

        toast.success("Успешная регистрация")

      } else {
        toast.error(json.message)
      }

    } catch (error) {
      toast.error("Произошла ошибка...")
      console.error(error)
    }
  }

  const signOutHandler = () => {

    setUser(null)
    setToken("")
    localStorage.removeItem("site")

    navigate("/signIn")
  }

  const loggedIn = () => {
    return token !== "";
  }

  return (
      <AuthContext.Provider value={{token, user, signUp: signUpHandler, signIn: signInHandler, signOut: signOutHandler, loggedIn: loggedIn}}>
        {children}
      </AuthContext.Provider>
  )
}

export default AuthProvider

export const useAuth = () => {
  return useContext(AuthContext)
}
