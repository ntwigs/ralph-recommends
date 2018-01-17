import * as React from 'react'
import { compose, withState, lifecycle} from 'recompose'
import { User } from './User'

const enhance = compose(
  withState('users', 'setUsers', []),
  lifecycle({
    async componentDidMount() {
      const usersData = await fetch('http://localhost:8080/users')
      const users = await usersData.json()


      const allNamesPromise = Array(2).fill(0).map(async () => {
        const namesData = await fetch('https://uinames.com/api/?amount=400')
        const names = await namesData.json()
        return names
      })

      const allNames = await Promise.all(allNamesPromise)
      const combined = allNames.reduce((prev, curr) => [...prev, ...curr], [])

      const completeUsers = users.map((id, index) => ({
        id,
        name: combined[index].name,
        surname: combined[index].surname,
        gender: combined[index].gender,
      }))

      this.props.setUsers(completeUsers)
    }
  })
)

export const App = enhance(({ users }) => {
  return users.map((user) => <User key={ user.id } { ...user }/>)
}) 