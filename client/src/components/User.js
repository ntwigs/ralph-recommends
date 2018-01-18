import * as React from 'react'
import { Card, Subhead, Text, Box, Button, Row, Column } from 'rebass'
import styled, { keyframes } from 'styled-components'
import { fadeInLeft } from 'react-animations'

const CustomButton = styled(Button)`
  margin-top: 10px;
`

const fadeInAnimation = keyframes`${fadeInLeft}`

const CustomSubText = styled(Subhead)`
  animation: ${fadeInAnimation} 1s ease;
  animation-delay: 1s;
`

const CustomText = styled(Text)`
  animation: ${fadeInAnimation} 1s ease;
  animation-delay: 1s;
`

const CustomCard = styled(Card)`
  animation: ${fadeInAnimation} 1s ease;
`

export const User = ({ id, name, surname, gender }) => (
  <CustomCard m={20}>
    <Box p={10}>
      <CustomSubText>{name}</CustomSubText>
      <CustomText children={surname} />
      <CustomButton children="select" />
    </Box>
  </CustomCard>
)
