import * as React from 'react'
import { Card, Subhead, Text, Box, Button, Row, Column } from 'rebass'
import styled, { keyframes } from 'styled-components'
import { Link } from 'react-router-dom'

const CustomButton = styled(Button)`
  margin-top: 10px;
`


const CustomSubText = styled(Subhead)`
  animation-delay: 1s;
`

const CustomText = styled(Text)`
  animation-delay: 1s;
`

const CustomCard = styled(Card)`
`

export const User = ({ id, name, surname, gender, onClick }) => (
  <CustomCard m={20} id={id}>
    <Box p={10}>
      <CustomSubText>{name}</CustomSubText>
      <CustomText children={surname} />
      <CustomButton onClick={onClick} children={<Link to={ `/${id}` } >Select</Link>} />
    </Box>
  </CustomCard>
)
