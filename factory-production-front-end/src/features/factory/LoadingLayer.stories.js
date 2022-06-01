import { Box, Grommet } from "grommet";
import { grommet } from "grommet/themes";
import { LoadingLayer } from "./LoadingLayer";

export default {
    title: 'LoadingLayer',
    component: LoadingLayer
}

export const Default = () => (
  <Grommet theme={grommet}>
    <Box align="center" pad="large">
      <LoadingLayer />
    </Box>
  </Grommet>
);