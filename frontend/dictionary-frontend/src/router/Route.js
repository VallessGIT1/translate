import React from 'react'
import {Navigate, Outlet} from 'react-router-dom'
import {useAuth} from '../hooks/AuthProvider'

export const PrivateRoute = () => {
  const user = useAuth()
  if (!user.token) {
    return <Navigate to="/signIn"/>
  }
  return <Outlet/>
}

export default PrivateRoute