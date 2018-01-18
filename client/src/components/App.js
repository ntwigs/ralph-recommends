import * as React from 'react'
import { compose, withState, lifecycle, branch, renderComponent } from 'recompose'
import { User } from './User'
import { Flex, Box, Text, Fixed} from 'rebass'

const enhance = compose(
  withState('users', 'setUsers', []),
  lifecycle({
    async componentWillMount() {
      const usersData = await fetch('http://localhost:8080/users')
      const users = await usersData.json()

      const allNamesPromise = Array(2)
        .fill(0)
        .map(async () => {
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
    },
  }),
  branch(({ users }) => !(users.length), renderComponent(() => <h1>Loading</h1>)),
)

export const App = enhance(({ users }) => (
  <Flex height="100vh">
    <Box w={1 / 5} px={2}>
      {users.map(user => <User key={user.id} {...user} />)}
    </Box>
    <Box w={3 / 4} px={2}>
      <Fixed right top w={3/4} m={28}>
        <Text p={1} color="white" bg="blue">
          Half
        </Text>
      </Fixed>
    </Box>
  </Flex>
))
