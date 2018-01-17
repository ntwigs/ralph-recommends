import * as React from 'react'
import { Card, Subhead, Text, Box, Button, Row, Column } from 'rebass'
import styled from 'styled-components'

const CustomButton = styled(Button)`
  margin-top: 10px;
`

export const User = ({ id, name, surname, gender }) => (
  <Card width={256}>
    <Box p={10}>
      <Subhead>{name}</Subhead>
      <Text children={surname} />
      <CustomButton children="select" />
    </Box>
  </Card>
)
