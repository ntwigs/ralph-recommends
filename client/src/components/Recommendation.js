import * as React from 'react'
import { Card, Text, Box, Subhead } from 'rebass'
import styled, {keyframes} from 'styled-components'

const CustomSubText = styled(Subhead)`
  animation-delay: 1s;
`

const CustomText = styled(Text)`
  animation-delay: 1s;
`

const CustomCard = styled(Card)`
`



export const Recommendation = ({ name, value }) => (
  <CustomCard m={20}>
    <Box p={10}>
      <CustomSubText>{name}</CustomSubText>
      <CustomText children={value.toFixed(1)} />
    </Box>
  </CustomCard>
)
