import * as React from 'react'
import {
  compose,
  withState,
  lifecycle,
  branch,
  renderComponent,
  withHandlers
} from 'recompose'
import { User } from './User'
import { Flex, Box, Text, Fixed } from 'rebass'
import { Recommendations } from './Recommendations'
import { Route, withRouter } from 'react-router-dom'
import { Recommendation } from './Recommendation';

const enhance = compose(
  withState('users', 'setUsers', []),
  withState('recommendations', 'setRec', { recommendations: {}, userRecs: {} }),
  lifecycle({
    async componentDidMount() {
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
  branch(({ users }) => !users.length, renderComponent(() => <h1>Loading</h1>)),
  withHandlers({
    onClick: ({ setRec }) => async ({ target }) => {
      const { id } = target.parentElement.parentElement.parentElement

      const response = await fetch(`http://localhost:8080?id=${ id }`) 
      const recommendations = await response.json()

      const res = await fetch(`http://localhost:8080/recs?id=${ id }`) 
      const userRecs = await res.json()

      if (response.status === 200) {
        setRec({recommendations, userRecs})
      }
    }
  }),
)

export const App = enhance(({ users, recommendations, onClick }) => (
  <Flex wrap>
    <Box w={1 / 5} px={2}>
      {users.map(user => <User key={user.id} {...user} onClick={onClick} />)}
    </Box>
    <Box w={2 / 5} px={2}>
        <Recommendations {...recommendations} type='recs' />
    </Box>
     <Box w={2 / 5} px={2}>
        <Recommendations {...recommendations} type='userrecs' />
    </Box>   
  </Flex>
))
