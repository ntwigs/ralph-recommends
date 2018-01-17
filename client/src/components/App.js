import * as React from 'react'
import { compose, withState, lifecycle} from 'recompose'

const enhance = compose(
  withState('users', 'setUsers', []),
  lifecycle({
    async componentWillMount() {
      const usersData = await fetch('http://localhost:8080/users')
      const users = await usersData.json()
      this.props.setUsers(users)
    }
  })
)

export const App = enhance(({ users }) => {
  return users.map((user) => <h1>{  user }</h1>)
}) 